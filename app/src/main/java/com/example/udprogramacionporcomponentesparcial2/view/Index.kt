package com.example.udprogramacionporcomponentesparcial2.view

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.udprogramacionporcomponentesparcial2.viewModel.listFilters


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Index(navController: NavController){
    var filter by remember { mutableIntStateOf(0) }
    var search by remember { mutableStateOf(TextFieldValue()) }
    val updateFilter:(Int)->Unit={filter = it}
    val updateSearch:(TextFieldValue)->Unit={search=it}
    Scaffold (
        modifier = Modifier.fillMaxSize(),
        topBar = { TopBarIndex(updateFilter,updateSearch)},
        content = {
        }
    )
}
@Composable
fun ComposeFilter(updateFilter:(Int)->Unit){
    var expanded by remember {mutableStateOf(false)}
    var index by remember {mutableIntStateOf(0)}
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ){
        Text(text = "Order BY: ${listFilters[index]}")
        Box() {
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
                        text = { Text(text = name)},
                        onClick = {
                            updateFilter(indexFilter)
                            index = indexFilter
                        }
                    )
                }
            }
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
            label = { Text(text = "Search")},
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
fun TopBarIndex(updateFilter:(Int)->Unit,updateSearch:(TextFieldValue)->Unit){
LazyColumn(
    modifier = Modifier.fillMaxWidth(),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
){
    item {
        Row(
            horizontalArrangement = Arrangement.Center
        ) {
            ComposeFilter(updateFilter)
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
fun ContentIndex(){

}
@Preview
@Composable
fun PreviewIndex(){
    Index(NavController(LocalContext.current))
}