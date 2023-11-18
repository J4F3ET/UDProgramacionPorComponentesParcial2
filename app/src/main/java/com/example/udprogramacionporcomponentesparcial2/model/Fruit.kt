package com.example.udprogramacionporcomponentesparcial2.model

import android.os.Parcel
import android.os.Parcelable

data class Fruit(
    val name: String,
    val id: Int,
    val family: String,
    val order: String,
    val genus: String,
    val nutritions: Nutritions
)
