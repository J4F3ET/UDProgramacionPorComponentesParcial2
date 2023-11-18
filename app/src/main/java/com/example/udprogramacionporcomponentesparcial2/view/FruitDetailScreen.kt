package com.example.udprogramacionporcomponentesparcial2.view

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.udprogramacionporcomponentesparcial2.viewModel.defaultFruit
import kotlin.reflect.KProperty1

@Composable
fun FruitDetailScreen(navController: NavController){
    val dataNutritions = listOf(
        Pair("Calories", defaultFruit.nutritions.calories.toString()),
        Pair("Fat", defaultFruit.nutritions.fat.toString()),
        Pair("Sugar", defaultFruit.nutritions.sugar.toString()),
        Pair("Carbohydrates", defaultFruit.nutritions.carbohydrates.toString()),
        Pair("Protein", defaultFruit.nutritions.protein.toString())
    )
    Column(
        modifier = Modifier
            .fillMaxSize(0.9f)
            .padding(20.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Row(horizontalArrangement = Arrangement.Start, modifier = Modifier.fillMaxWidth(0.9f)){
            Text(text = "Fruit Detail: ${defaultFruit.name}")
        }
        Row(horizontalArrangement = Arrangement.Start, modifier = Modifier.fillMaxWidth(0.9f)){
            Text(text = "Nutritions", style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold))
        }
        Row (
            horizontalArrangement = Arrangement.Center
        ){
            Box(
                contentAlignment = Alignment.Center
            ){
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, Color.Black, RoundedCornerShape(0.dp, 12.dp, 0.dp, 12.dp))
                    .padding(10.dp)
                ){
                    LazyColumn{
                        items(dataNutritions){(property, value) ->
                            Text(text = "$property: $value")
                        }
                    }
                }
            }
        }
        Row {
            OutlinedButton(onClick = { navController.popBackStack()}) {
                Text(text = "Volver")
            }
        }
    }


}