package com.example.appviagensfinal.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.appviagensfinal.repository.CategoriaDespesaRepository

class CategoriaViewModelFactory(val app: Application) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val repository = CategoriaDespesaRepository(app)
        val model = CategoriaViewModel(repository)
        return model as T
    }
}