package com.example.appviagensfinal.telas

import android.app.Application
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.appviagensfinal.R
import com.example.appviagensfinal.ScreenManager
import com.example.appviagensfinal.componentes.CustomTopAppBar
import com.example.appviagensfinal.componentes.PasswordField
import com.example.appviagensfinal.viewModel.PessoaViewModel
import com.example.appviagensfinal.viewModel.PessoaViewModelFactory


@Composable
fun CadastroCompose(navController: NavHostController) {
    Scaffold(
        topBar = {
            CustomTopAppBar(navController, "", true)
        }, content = {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

            }

        })
    val ctx = LocalContext.current
    val app = ctx.applicationContext as Application
    val model:
            PessoaViewModel = viewModel(
        factory = PessoaViewModelFactory(app)
    )
    Column(
        modifier = Modifier.padding(10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.padding(13.dp))
        Text(
            text = "Cadastro",
            style = TextStyle(fontSize = 40.sp, fontFamily = FontFamily.SansSerif, fontWeight = FontWeight.Bold)
        )
        Spacer(modifier = Modifier.height(25.dp))
        TextField(
            label = { Text(text = "Nome") },
            value = model.nome,
            onValueChange = { model.nome = it })
        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            label = { Text(text = "Login") },
            value = model.login,
            onValueChange = { model.login = it })

        Spacer(modifier = Modifier.height(20.dp))
        PasswordField(value = model.senha, onChange = { model.senha = it })
        Spacer(modifier = Modifier.height(20.dp))
        var confirmeSenha by remember { mutableStateOf("") }
        PasswordField(
            value = confirmeSenha,
            onChange = { confirmeSenha = it },
            label = "Confirme a senha"
        )

        Spacer(modifier = Modifier.height(25.dp))
        val context = LocalContext.current
        var button by remember { mutableStateOf(false) }

        button = model.senha == confirmeSenha && model.senha != "" && confirmeSenha != ""

        Button(
            enabled = button,
            onClick = {
                Toast
                    .makeText(
                        context,
                        "Cadastrado com sucesso!",
                        Toast.LENGTH_SHORT
                    )
                    .show()
                model.salvar()
                navController.navigate(ScreenManager.Login.route)
            },
            modifier = Modifier
                .width(200.dp)
                .height(50.dp)
        ) {
            Text(text = "Cadastrar-se")
        }

    }
}