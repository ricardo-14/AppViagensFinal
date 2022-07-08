package com.example.appviagensfinal.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appviagensfinal.model.CategoriaDespesa
import com.example.appviagensfinal.repository.CategoriaDespesaRepository
import kotlinx.coroutines.launch

class CategoriaViewModel(
    private val repository: CategoriaDespesaRepository
) : ViewModel() {

    var id by mutableStateOf(0)
    var nome by mutableStateOf("")

    private val _isIDInsert = MutableLiveData<Int>(0)
    val retornoInsert: LiveData<Int>
        get() = _isIDInsert

    fun salvar() {
        val categoria = CategoriaDespesa(id, nome)
        viewModelScope.launch {
            _isIDInsert.postValue(repository.save(categoria).toInt())
        }

    }

    fun findAll(): LiveData<List<CategoriaDespesa>> {
        return repository.findAll()
    }

    fun deleteByID(id: Int) {
        viewModelScope.launch {
            repository.deleteByID(id)
        }
    }
}