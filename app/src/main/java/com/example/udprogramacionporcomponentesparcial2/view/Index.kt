package com.example.udprogramacionporcomponentesparcial2.view

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.udprogramacionporcomponentesparcial2.model.Fruit
import com.example.udprogramacionporcomponentesparcial2.viewModel.services.RetrofitService


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Index(navController: NavController){
    var list = remember { mutableListOf<Fruit?>(null) }
    val key = remember { mutableStateOf(0) }

    // Define la lambda que se ejecutará en segundo plano
    LaunchedEffect(key) {
        val response = RetrofitService().getFruits()
        key.value = response.size
        list.addAll(response)
    }
    Scaffold (
        modifier = Modifier.fillMaxSize(),
        content = {
            Text(text = list.toString())
        }
    )
}