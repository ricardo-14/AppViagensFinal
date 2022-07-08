package com.example.appviagensfinal.model

import androidx.room.*

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = CategoriaDespesa::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("categoriaID"),
        ),
        ForeignKey(
            entity = Viagem::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("viagemID"),
        )]
)
data class Despesa(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    val descricao: String,
    val valor: Double,
    val local: String,
    val data: String,
    val categoriaID: Int,
    val viagemID: Int
) {

}

data class DespesaCategoria(
    @Embedded val categoria: CategoriaDespesa,
    @Relation(
        parentColumn = "id",
        entityColumn = "categoriaID"
    )
    val despesas: Despesa
)