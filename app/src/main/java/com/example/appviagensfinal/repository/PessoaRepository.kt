package com.example.appviagensfinal.repository

import android.app.Application
import com.example.appviagensfinal.dao.Connection
import com.example.appviagensfinal.dao.PessoaDao
import com.example.appviagensfinal.model.Pessoa

class PessoaRepository(app: Application) {

    private val pessoaDao: PessoaDao

    init {
        pessoaDao = Connection
            .getDB(app).pessoaDao()
    }

    suspend fun save(pessoa: Pessoa) {
        if (pessoa.id == 0) {
            pessoaDao.insert(pessoa)
        }
        else {
            pessoaDao.update(pessoa)
        }
    }

    suspend fun login(login: String, senha: String): Pessoa? = pessoaDao.login(login,senha)

}