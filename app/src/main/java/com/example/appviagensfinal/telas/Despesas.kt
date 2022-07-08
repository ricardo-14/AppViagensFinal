package com.example.appviagensfinal.telas

import android.app.Application
import android.widget.Toast
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.R
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.rounded.Paid
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.navigation
import com.example.appviagensfinal.componentes.CustomTopAppBar
import com.example.appviagensfinal.model.DespesaCategoria
import com.example.appviagensfinal.viewModel.DespesaViewModel
import com.example.appviagensfinal.viewModel.DespesaViewModelFactory
import java.text.DecimalFormat


@Composable
fun DespesasCompose(navController: NavHostController, idViagem: Int, destinoViagem: String) {
    Scaffold(
        topBar = { CustomTopAppBar(navController, "Despesas da viagem", true) },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("formDespesa/$idViagem") }) {
                Icon(Icons.Filled.Add, contentDescription = "")
            }
        },
        isFloatingActionButtonDocked = true,
    ) {
        Column(
            modifier = Modifier.padding(10.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.padding(7.dp))
            ListaDespesas(navController = navController, idViagem)
        }

    }
}

fun NavGraphBuilder.formDespesaGrap(navController: NavHostController, idUserLogged: Int) {
    navigation(startDestination = "principal", route = "despesas") {
        composable("principal") { ViagensCompose(navController, idUserLogged) }
        composable("formDespesa/{despesaID}",
            arguments = listOf(
                navArgument("despesaID") {
                    type = NavType.IntType
                }
            )
        ) {
            val id = it.arguments?.getInt("despesaID")
            FormDespesaCompose(navController, id, 0)
        }
        composable("formDespesaEditar/{viagemID}/{despesaID}",
            arguments = listOf(
                navArgument("viagemID") {
                    type = NavType.IntType
                },
                navArgument("despesaID") {
                    type = NavType.IntType
                }
            )
        ) {
            val idViagem = it.arguments?.getInt("viagemID")
            val idDespesa = it.arguments?.getInt("despesaID")
            FormDespesaCompose(navController, idViagem, idDespesa)
        }
    }
}

@Composable
fun ListaDespesas(navController: NavHostController, idViagem: Int) {
    val ctx = LocalContext.current
    val app = ctx.applicationContext as Application
    val model:
            DespesaViewModel = viewModel(
        factory = DespesaViewModelFactory(app)
    )
    val despesas by model.allDespesasByViagem(idViagem).observeAsState(listOf())
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        items(items = despesas) { d ->
            DespesasView(navController = navController, d, model)
        }
    }
}

@Composable
fun DespesasView(
    navController: NavHostController,
    index: DespesaCategoria,
    model: DespesaViewModel
) {
    val df = DecimalFormat("0.00")
    val context = LocalContext.current

    var aExcluir by remember {
        mutableStateOf(false)
    }
    if (aExcluir) {
        val openDialog = remember { mutableStateOf(true) }
        AlertDialog(
            onDismissRequest = { openDialog.value = false },
            text = {
                Text(
                    "Deseja excluir essa despesa?",
                    style = MaterialTheme.typography.h6,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        model.deleteByID(index.despesas.id)
                        Toast
                            .makeText(
                                context,
                                "Despesa apagada!",
                                Toast.LENGTH_SHORT
                            )
                            .show()
                        aExcluir = false
                    }
                ) {
                    Text(
                        "Sim",
                        color = Color.Black,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        openDialog.value = false
                        aExcluir = false
                    }
                ) {
                    Text("Não",
                        color = Color.Black,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold)
                }
            },
            backgroundColor = Color.White,
            contentColor = Color.Black
        )
    }
    Card(
        elevation = 10.dp,
        shape = RoundedCornerShape(5.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 8.dp)
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = {
                        navController.navigate("formDespesaEditar/" + index.despesas.viagemID + "/" + index.despesas.id)
                    },
                    onLongPress = {
                        aExcluir = true
                    },
                )
            }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Spacer(modifier = Modifier.padding(5.dp))
            Icon(
                imageVector = Icons.Rounded.Paid,
                contentDescription = null,
                modifier = Modifier
                    .size(60.dp)
                    .padding(vertical = 5.dp)
            )
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .weight(1f)
            ) {
                Text(
                    text = index.categoria.nome,
                    style = MaterialTheme.typography.h5,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.padding(3.dp))
                Text(
                    text = "Local: " + index.despesas.local,
                    style = MaterialTheme.typography.caption
                )
                Spacer(modifier = Modifier.padding(3.dp))
                Text(
                    text = "Data: " + index.despesas.data,
                    style = MaterialTheme.typography.caption
                )
                Spacer(modifier = Modifier.padding(3.dp))
                Text(
                    text = "Descrição: " + index.despesas.descricao,
                    style = MaterialTheme.typography.caption
                )
                Spacer(modifier = Modifier.padding(5.dp))
            }
            Text(
                text = "R$ ${df.format(index.despesas.valor)}",
                style = MaterialTheme.typography.h6,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(16.dp)

            )
        }
    }

}