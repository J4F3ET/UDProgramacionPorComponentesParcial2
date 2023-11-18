package com.example.udprogramacionporcomponentesparcial2.viewModel

import com.example.udprogramacionporcomponentesparcial2.model.Fruit
import com.example.udprogramacionporcomponentesparcial2.model.Nutritions

val listFilters = listOf("Calorías","Grasas","Azúcar","Carbohidratos","Proteína")
var defaultFruit = Fruit(
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