package com.example.appviagensfinal

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Flight
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class ScreenManager(
    val route: String,
    @StringRes val resourceId: Int,
    val icon: ImageVector
) {
    object Login : ScreenManager("login", R.string.login, Icons.Filled.Email)
    object Cadastro : ScreenManager("cadastro", R.string.cadastro, Icons.Filled.Email)
    object EsqueciSenha : ScreenManager("EsqueciSenha", R.string.EsqueciSenha, Icons.Filled.Email)
    object Home : ScreenManager("home", R.string.home, Icons.Filled.Home)
    object Viagens : ScreenManager("viagens", R.string.viagens, Icons.Filled.Flight)
    object Despesas : ScreenManager("despesas", R.string.despesas, Icons.Filled.Flight)
    object FormViagem : ScreenManager("FormViagem", R.string.formViagem, Icons.Filled.Flight)
    object Sobre : ScreenManager("sobre", R.string.sobre, Icons.Filled.Person)

}