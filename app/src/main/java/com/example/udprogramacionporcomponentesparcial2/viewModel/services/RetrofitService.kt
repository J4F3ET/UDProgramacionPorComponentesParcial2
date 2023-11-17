package com.example.udprogramacionporcomponentesparcial2.viewModel.services

import android.util.Log
import com.example.udprogramacionporcomponentesparcial2.model.Fruit
import com.example.udprogramacionporcomponentesparcial2.model.IRepository
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@OptIn(DelicateCoroutinesApi::class)
class RetrofitService {
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://www.fruityvice.com/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val apiService: IRepository = retrofit.create(IRepository::class.java)
    lateinit var fruits:List<Fruit>
    init {
        GlobalScope.launch(Dispatchers.IO){
            fruits = fetchFruits()
        }
    }
    private suspend fun fetchFruits(): List<Fruit> {
        return try {
            apiService.getFruits()
        } catch (e: Exception) {
            Log.e("Error fetching fruits:", "${e.message}")
            emptyList()
        }
    }
}
