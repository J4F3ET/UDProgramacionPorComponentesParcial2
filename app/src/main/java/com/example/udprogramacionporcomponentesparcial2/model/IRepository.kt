package com.example.udprogramacionporcomponentesparcial2.model

import retrofit2.http.GET

interface IRepository {
    @GET("fruit/all")
    suspend fun getFruits(): List<Fruit>
}