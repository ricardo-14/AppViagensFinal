package com.example.appviagensfinal.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appviagensfinal.model.Despesa
import com.example.appviagensfinal.model.DespesaCategoria
import com.example.appviagensfinal.repository.DespesaRepository
import kotlinx.coroutines.launch

class DespesaViewModel(
    private val repository: DespesaRepository
) : ViewModel() {

    var id by mutableStateOf(0)
    var descricao by mutableStateOf("")
    var valor by mutableStateOf(0.00)
    var local by mutableStateOf("")
    var data by mutableStateOf("")
    var categoriaID by mutableStateOf(0)
    var viagemID by mutableStateOf(0)


    fun salvar() {
        val despesa = Despesa(id, descricao, valor, local, data, categoriaID, viagemID)
        viewModelScope.launch {
            repository.save(despesa)
        }
    }

    fun allDespesasByViagem(viagemID: Int): LiveData<List<DespesaCategoria>> {
        return repository.allDespesasByViagem(viagemID)
    }

    fun findById(id: Int) {
        viewModelScope.launch {
            val d = repository.findById(id)
            descricao = d.descricao
            valor = d.valor
            local = d.local
            data = d.data
            categoriaID = d.categoriaID
            viagemID = d.viagemID
        }
    }

    fun deleteByID(id: Int) {
        viewModelScope.launch {
            repository.deleteByID(id)
        }
    }
}