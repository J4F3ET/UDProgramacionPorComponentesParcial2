package com.example.udprogramacionporcomponentesparcial2.viewModel.navigation
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.udprogramacionporcomponentesparcial2.view.FruitDetailScreen
import com.example.udprogramacionporcomponentesparcial2.view.Index

@Composable
fun AppNavigation(){
    val navController = rememberNavController()
    NavHost(navController = navController,startDestination = AppScreens.Index.router){
        composable(route = AppScreens.Index.router){ Index(navController)}
        composable(route = AppScreens.FruitDetailScreen.router){FruitDetailScreen(navController)}
    }
}