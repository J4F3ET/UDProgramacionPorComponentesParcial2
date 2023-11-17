package com.example.udprogramacionporcomponentesparcial2.viewModel.services

import com.example.udprogramacionporcomponentesparcial2.model.ApiService
import com.example.udprogramacionporcomponentesparcial2.model.Fruit
import com.example.udprogramacionporcomponentesparcial2.model.Nutritions
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.Retrofit




class RetrofitService():ApiService  {
    val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
    val retrofit = Retrofit.Builder()
        .baseUrl("https://www.fruityvice.com/api/fruit/all/")
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    val apiService = retrofit.create(ApiService::class.java)

    override suspend fun getFruits(): List<Fruit> {
        return apiService.getFruits()
    }
}
