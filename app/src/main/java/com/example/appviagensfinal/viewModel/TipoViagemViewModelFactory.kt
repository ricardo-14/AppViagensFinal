package com.example.appviagensfinal.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.appviagensfinal.repository.TipoViagemRepository

class TipoViagemViewModelFactory(val app: Application) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val repository = TipoViagemRepository(app)
        val model = TipoViagemViewModel(repository)
        return model as T
    }
}