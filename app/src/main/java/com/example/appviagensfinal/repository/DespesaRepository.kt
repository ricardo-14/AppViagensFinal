package com.example.appviagensfinal.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.appviagensfinal.dao.Connection
import com.example.appviagensfinal.dao.DespesaDao
import com.example.appviagensfinal.model.Despesa
import com.example.appviagensfinal.model.DespesaCategoria

class DespesaRepository(app: Application) {

    private val despesaDao: DespesaDao

    init {
        despesaDao = Connection
            .getDB(app).DespesaDao()
    }

    suspend fun save(despesa: Despesa) {
        if (despesa.id == 0) {
            despesaDao.insert(despesa)
        } else {
            despesaDao.update(despesa)
        }
    }

    fun allDespesasByViagem(viagemID: Int): LiveData<List<DespesaCategoria>> {
        return despesaDao.allDespesasByViagem(viagemID)
    }

    suspend fun findById(id: Int) : Despesa {
        return despesaDao.findById(id)
    }

    suspend fun deleteByID(id : Int) {
        despesaDao.deleteByID(id)
    }
}