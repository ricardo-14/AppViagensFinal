package com.example.appviagensfinal.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appviagensfinal.model.TipoViagem
import com.example.appviagensfinal.repository.TipoViagemRepository
import kotlinx.coroutines.launch

class TipoViagemViewModel(
    private val repository: TipoViagemRepository
) : ViewModel() {

    var tipo by mutableStateOf("")

    fun salvar() {
        val tipoViagem = TipoViagem(tipo)
        viewModelScope.launch {
            repository.save(tipoViagem)
        }
    }
}