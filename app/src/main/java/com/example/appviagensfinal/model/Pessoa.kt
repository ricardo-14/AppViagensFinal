package com.example.appviagensfinal.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Pessoa(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    val nome: String,
    val login: String,
    val senha: String,
) {

}