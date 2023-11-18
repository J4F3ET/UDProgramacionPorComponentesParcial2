package com.example.udprogramacionporcomponentesparcial2.view

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.udprogramacionporcomponentesparcial2.model.Fruit
import com.example.udprogramacionporcomponentesparcial2.viewModel.defaultFruit
import com.example.udprogramacionporcomponentesparcial2.viewModel.listFilters
import com.example.udprogramacionporcomponentesparcial2.viewModel.navigation.AppScreens
import com.example.udprogramacionporcomponentesparcial2.viewModel.services.RetrofitService



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Index(navController: NavController){
    val service = RetrofitService()
    var filter by remember { mutableIntStateOf(0) }
    var checked by remember {mutableStateOf(false)}
    var sizeSearch by remember {mutableIntStateOf(0)}
    var allFruitsByFilter by remember {mutableStateOf<List<Fruit>>(service.findFruitsByFilter(filter,checked))}
    var listFruit by remember {mutableStateOf<List<Fruit>>(service.fruits)}
    LaunchedEffect(listFruit){
        listFruit = service.fetchFruits()
        if(listFruit.isNotEmpty()){
            allFruitsByFilter = service.findFruitsByFilter(filter,checked)
            sizeSearch = allFruitsByFilter.size
        }
    }
    val updateFilter:(Int)->Unit={
        filter = it
        allFruitsByFilter = service.findFruitsByFilter(it,checked)
    }
    val updateSearch:(TextFieldValue)->Unit={
        allFruitsByFilter=service.fruitByName(it.text)
    }
    val updateCheck:(Boolean)->Unit={
        checked = it
        allFruitsByFilter= service.findFruitsByFilter(filter,it)
    }
    Scaffold (
        modifier = Modifier.fillMaxSize(),
        topBar = { TopBarIndex(updateFilter,updateCheck,updateSearch)},
        content = {
            Box(
                modifier = Modifier.padding(it),
                contentAlignment = Alignment.TopCenter
            ){
                ContentIndex(allFruitsByFilter,navController)
            }
        }
    )
}
@Composable
fun ComposeFilter(updateFilter:(Int)->Unit,updateCheck:(Boolean)->Unit){
    var expanded by remember {mutableStateOf(false)}
    var index by remember {mutableIntStateOf(0)}
    Row(
        modifier = Modifier.fillMaxWidth(0.9f),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ){
        Text(text = "\tOrder BY: ${listFilters[index]}", fontSize = 10.sp, lineHeight = 11.sp)
        Box{
            IconButton(onClick = { expanded = !expanded }) {
                Icon(Icons.Default.MoreVert, "")
            }
            DropdownMenu(
                modifier = Modifier.border(1.dp, Color.Black),
                expanded = expanded,
                onDismissRequest = {expanded = false}
            ){
                listFilters.forEachIndexed{indexFilter,name->
                    DropdownMenuItem(
                        text = { Text(text = name, fontSize = 10.sp, lineHeight = 11.sp)},
                        onClick = {
                            updateFilter(indexFilter)
                            index = indexFilter
                        }
                    )
                }
            }
        }
        CheckableRow(updateCheck)
    }
}
@Composable
fun CheckableRow(updateCheck:(Boolean)->Unit) {
    MaterialTheme {
        var checked by remember { mutableStateOf(false) }
        Row(
            Modifier
                .toggleable(
                    value = checked,
                    role = Role.Checkbox,
                    onValueChange = {
                        updateCheck(!checked)
                        checked = !checked
                    }
                )
                .padding(10.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(checked = checked, onCheckedChange = null)
            Text("Mayor a menor", Modifier.weight(1f), fontSize = 10.sp, lineHeight = 11.sp)
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComposeSearch(updateSearch:(TextFieldValue)->Unit){
    var text by remember { mutableStateOf(TextFieldValue()) }
    val updateText:(TextFieldValue)->Unit={text = it}
    Row(
        verticalAlignment = Alignment.CenterVertically
    ){
        OutlinedTextField(
            label = { Text(text = "Search by name")},
            value = text,
            enabled = true,
            onValueChange ={
                updateSearch(it)
                updateText(it)
            }
        )
    }
}
@Composable
fun TopBarIndex(updateFilter:(Int)->Unit,updateCheck:(Boolean)->Unit,updateSearch:(TextFieldValue)->Unit){
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        item {
            Row(
                horizontalArrangement = Arrangement.Center
            ) {
                ComposeFilter(updateFilter,updateCheck)
            }
        }
        item {
            Row {
                ComposeSearch(updateSearch)
            }
        }
    }
}
@Composable
fun ContentIndex(listFruit:List<Fruit>,navController: NavController){
    LazyColumn(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ){
        item{
            Text(text = "Total Result ${listFruit.size}")
        }
        item {
            GridFruits(listFruit,navController)
        }
    }
}
@Composable
fun GridFruits(fruits:List<Fruit>,navController: NavController){
    val maxWidthInDp = (LocalContext.current.resources.displayMetrics.widthPixels.times(0.9)).dp
    val maxHeightDp = (LocalContext.current.resources.displayMetrics.heightPixels.div(2)).dp
    LazyVerticalGrid(
        userScrollEnabled = true,
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .width(maxWidthInDp)
            .height(maxHeightDp),
        verticalArrangement = Arrangement.Top,
        horizontalArrangement = Arrangement.Center,
        content = {
            items(fruits){
                CellFruit(fruit = it, navController = navController,Pair(200.dp,70.dp))
          }
        }
    )
}
@Composable
fun CellFruit(fruit: Fruit,navController: NavController,area:Pair<Dp,Dp>){
    Box(modifier = Modifier.padding(2.dp)){
        Box(
            modifier = Modifier
                .border(0.5.dp, Color.Black, RoundedCornerShape(0.dp, 8.dp, 0.dp, 8.dp))
                .width(area.first)
                .height(area.second)
                .shadow(1.dp, RoundedCornerShape(0.dp, 8.dp, 0.dp, 8.dp))
                .clickable {
                    defaultFruit = fruit
                    navController.navigate(route = AppScreens.FruitDetailScreen.router)
                },
            contentAlignment = Alignment.Center
        ){
            Text(text = fruit.name, color = Color.DarkGray)
        }
    }
}