package com.example.udprogramacionporcomponentesparcial2.view

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.udprogramacionporcomponentesparcial2.model.Fruit
import com.example.udprogramacionporcomponentesparcial2.model.Nutritions
val defaultFruit = Fruit(
    "Default Fruit",
    0,"None",
    "None",
    "None",
    Nutritions(
        0,
        0.0,
        0.0,
        0.0,
        0.0
    )
)
@Composable
fun FruitDetailScreen(navController: NavController,fruit: Fruit = defaultFruit){


}