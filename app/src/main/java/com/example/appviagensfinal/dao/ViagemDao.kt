package com.example.appviagensfinal.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.appviagensfinal.model.Viagem

@Dao
interface ViagemDao {

    @Insert()
    suspend fun insert(viagem: Viagem)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(viagem: Viagem)

    @Query("Select * from Viagem v where v.usuarioID = :userID")
    fun getViagensByUser(userID: Int): LiveData<List<Viagem>>

    @Query("Select * from Viagem v where v.id = :id")
    suspend fun findById(id: Int): Viagem

    @Query("Delete from Viagem where Viagem.id = :id")
    suspend fun deleteByID(id: Int)

    @Query("select destino from Viagem where Viagem.id = :id")
    fun getDestinoByViagem(id: Int): String

    @Query("SELECT CASE WHEN (SELECT COUNT(*) from Despesa where viagemID = :id) > 0 THEN (SELECT SUM(valor) FROM Despesa as d inner join viagem v on d.viagemID = v.id where v.id = :id) ELSE 0.00 END;")
    fun somaDespesasByViagem(id: Int): LiveData<Double>

}