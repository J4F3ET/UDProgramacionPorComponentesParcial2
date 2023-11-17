package com.example.udprogramacionporcomponentesparcial2.model

import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("/")
    suspend fun getFruits(): List<Fruit>
}