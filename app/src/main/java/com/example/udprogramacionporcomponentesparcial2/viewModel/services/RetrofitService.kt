package com.example.udprogramacionporcomponentesparcial2.viewModel.services

import com.example.udprogramacionporcomponentesparcial2.model.ApiService
import com.example.udprogramacionporcomponentesparcial2.model.Fruit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.Retrofit

class RetrofitService: ApiService  {

    private val apiService = Retrofit.Builder()
        .baseUrl("https://www.fruityvice.com/api/fruit/all")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create(ApiService::class.java)

    override suspend fun getFruits(): List<Fruit> {
        return apiService.getFruits()
    }
}