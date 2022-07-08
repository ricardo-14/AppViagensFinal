package com.example.appviagensfinal.viewModel

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appviagensfinal.model.Viagem
import com.example.appviagensfinal.repository.ViagemRepository
import kotlinx.coroutines.launch

class ViagemViewModel(
    private val repository: ViagemRepository
) : ViewModel() {

    var id by mutableStateOf(0)
    var destino by mutableStateOf("")
    var dataPartida by mutableStateOf("")
    var dataChegada by mutableStateOf("")
    var orcamento by mutableStateOf(0.00)
    var tipoID by mutableStateOf(0)
    var usuarioID by mutableStateOf(0)

    fun salvar() {
        val viagem = Viagem(id, destino, dataPartida, dataChegada, orcamento, tipoID, usuarioID)
        viewModelScope.launch {
            repository.save(viagem)
        }
    }

    fun allViagensByUser(userID: Int): LiveData<List<Viagem>> {
        return repository.allViagensByUser(userID)
    }

    fun findById(id: Int) {
        viewModelScope.launch {
            val v = repository.findById(id)
            destino = v.destino
            dataPartida = v.dataPartida
            dataChegada = v.dataChegada
            orcamento = v.orcamento
            tipoID = v.tipoID
        }
    }

    fun deleteByID(id: Int) {
        viewModelScope.launch {
            repository.deleteByID(id)
        }
    }

    @Composable
    fun somaDespesasByViagem(idViagem: Int): LiveData<Double> {
        return repository.somaDespesasByViagem(idViagem)
    }

}