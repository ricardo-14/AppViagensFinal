package com.example.appviagensfinal.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.appviagensfinal.repository.PessoaRepository

class PessoaViewModelFactory(val app: Application) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val repository = PessoaRepository(app)
        val model = PessoaViewModel(repository)
        return model as T
    }

}