package com.example.appviagensfinal.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.appviagensfinal.dao.CategoriaDao
import com.example.appviagensfinal.dao.Connection
import com.example.appviagensfinal.model.CategoriaDespesa

class CategoriaDespesaRepository(app: Application) {

    private val categoriaDao: CategoriaDao

    init {
        categoriaDao = Connection
            .getDB(app).CategoriaDao()
    }

    suspend fun save(categoria: CategoriaDespesa): Long {
        if (categoria.id == 0) {
            return categoriaDao.insert(categoria)
        } else {
            categoriaDao.update(categoria)
        }
        return 0
    }

    fun findAll(): LiveData<List<CategoriaDespesa>> {
        return categoriaDao.findAll()
    }

    suspend fun deleteByID(id : Int) {
        categoriaDao.deleteByID(id)
    }
}