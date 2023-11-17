package com.example.udprogramacionporcomponentesparcial2.model

import retrofit2.http.GET

interface ApiService {
    @GET("/fruits")
    suspend fun getFruits(): List<Fruit>
}