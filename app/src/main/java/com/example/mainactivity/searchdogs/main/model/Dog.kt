package com.example.mainactivity.searchdogs.main.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Dog(
    @PrimaryKey
    val breed: String,
    val imageUrl: String?,
    var isTopDog: Boolean = false
)