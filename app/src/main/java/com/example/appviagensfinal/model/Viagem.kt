package com.example.appviagensfinal.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = Pessoa::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("usuarioID"),
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = TipoViagem::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("tipoID"),
        )]
)
data class Viagem(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    val destino: String,
    val dataPartida: String,
    val dataChegada: String,
    val orcamento: Double,
    val tipoID: Int,
    val usuarioID: Int
)