package com.example.appviagensfinal.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.appviagensfinal.model.Despesa
import com.example.appviagensfinal.model.DespesaCategoria

@Dao
interface DespesaDao {

    @Insert()
    suspend fun insert(despesa: Despesa)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(despesa: Despesa)

    @Transaction
    @Query("select * from Despesa d inner join CategoriaDespesa cd on d.categoriaID = cd.id where d.viagemID = :viagemID")
    fun allDespesasByViagem(viagemID: Int): LiveData<List<DespesaCategoria>>

    @Query("select * from Despesa d where d.id = :id")
    suspend fun findById(id: Int): Despesa

    @Query("Delete from Despesa where Despesa.id = :id")
    suspend fun deleteByID(id : Int)

}