package com.example.appviagensfinal.dao

import androidx.room.*
import com.example.appviagensfinal.model.Pessoa

@Dao
interface PessoaDao {

    @Insert()
    suspend fun insert(pessoa: Pessoa)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(pessoa: Pessoa)

    @Query("select * from Pessoa p where p.login = :login and p.senha = :senha")
    suspend fun login(login: String, senha: String): Pessoa?

}