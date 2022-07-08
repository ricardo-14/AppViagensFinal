package com.example.appviagensfinal.repository

import android.app.Application
import com.example.appviagensfinal.dao.Connection
import com.example.appviagensfinal.dao.TipoViagemDao
import com.example.appviagensfinal.model.TipoViagem

class TipoViagemRepository(app: Application) {

    private val tipoViagemDao: TipoViagemDao

    init {
        tipoViagemDao = Connection
            .getDB(app).tipoViagemDao()
    }

    suspend fun save(tipoViagem: TipoViagem) {
        if (tipoViagem.id == 0) {
            tipoViagemDao.insert(tipoViagem)
        }
    }
}