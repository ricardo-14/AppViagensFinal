package com.example.appviagensfinal.dao

import androidx.room.Dao
import androidx.room.Insert
import com.example.appviagensfinal.model.TipoViagem

@Dao
interface TipoViagemDao {

    @Insert()
    suspend fun insert(tipoViagem: TipoViagem)

}