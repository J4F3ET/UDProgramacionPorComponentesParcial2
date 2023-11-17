package com.example.udprogramacionporcomponentesparcial2.view

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController



@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Index(navController: NavController){
    var filter by remember { mutableIntStateOf(0) }
    val updateFilter:(Int)->Unit={
        filter = it
    }
    Scaffold (
        modifier = Modifier.fillMaxSize(),
        topBar = { TopBarIndex(updateFilter)},
        content = {

        }
    )
}
@Composable
fun ComposeFilter(updateFilter:(Int)->Unit){
    var expanded by remember {mutableStateOf(false)
    }
    val list = listOf("Calorías","Grasas","Azúcar","Carbohidratos","Protína")
    Row(horizontalArrangement = Arrangement.Center) {
        Text(text = "Order BY")
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = {expanded = false}
        ){
            list.forEachIndexed{index,name->
                DropdownMenuItem(text = { name}, onClick = { updateFilter(index)})
            }
            
        }
    }
}
@Composable
fun ComposeSearch(){

}
@Composable
fun TopBarIndex(updateFilter:(Int)->Unit){
LazyColumn(){
    item {
        Row {
            ComposeFilter(updateFilter)
        }
    }
    item {
        Row {
            ComposeSearch()
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