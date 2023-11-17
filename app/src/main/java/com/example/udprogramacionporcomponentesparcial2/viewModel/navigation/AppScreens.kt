package com.example.udprogramacionporcomponentesparcial2.viewModel.navigation

sealed class AppScreens(val router:String){
    object Index:AppScreens("index")
    object FruitDetailScreen:AppScreens("fruitDetailScreen")
}
