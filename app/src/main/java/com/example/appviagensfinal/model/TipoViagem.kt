package com.example.appviagensfinal.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TipoViagem(

    val tipo: String //INSERT INTO TipoViagem VALUES('Lazer', 1) INSERT INTO TipoViagem VALUES('Negócios', 2)

) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}