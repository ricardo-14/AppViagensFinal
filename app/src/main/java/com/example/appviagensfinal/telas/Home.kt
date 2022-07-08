package com.example.appviagensfinal.telas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.FlightTakeoff
import androidx.compose.material.icons.rounded.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.appviagensfinal.ScreenManager
import com.example.appviagensfinal.componentes.AppBarTelas
import com.example.appviagensfinal.ui.theme.AzulTheme

@Composable
fun HomeCompose(nameUserLogged: String) {
    Scaffold(
        topBar = { AppBarTelas("Home") }
    ) {
        Column(
            modifier = Modifier.padding(10.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.padding(6.dp))
            Text(
                text = "Olá, $nameUserLogged",
                style = TextStyle(fontSize = 40.sp, fontFamily = FontFamily.SansSerif, fontWeight = FontWeight.Bold)
            )
            Spacer(modifier = Modifier.padding(7.dp))
        }
    }
}

@Composable
fun HomeNavigation(nameUserLogged: String, idUserLogged: Int) {

    val navController = rememberNavController()
    val items = listOf(
        ScreenManager.Home,
        ScreenManager.Viagens,
        ScreenManager.Sobre
    )
    Scaffold(
        bottomBar = {
            BottomNavigation(
                backgroundColor = AzulTheme,

            ) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route
                items.forEach { item ->
                    BottomNavigationItem(
                        icon = { Icon(item.icon, contentDescription = null) },
                        label = {
                            Text(
                                text = stringResource(item.resourceId),
                                fontSize = 12.sp
                            )
                        },
                        selectedContentColor = Color.White,
                        unselectedContentColor = Color.White.copy(0.4f),
                        alwaysShowLabel = true,
                        selected = currentRoute == item.route,
                        onClick = {
                            navController.navigate(item.route) {
                                navController.graph.startDestinationRoute?.let { screen_route ->
                                    popUpTo(screen_route) {
                                        saveState = true
                                    }
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = ScreenManager.Home.route,
            Modifier.padding(innerPadding)
        ) {
            composable(ScreenManager.Home.route) { HomeCompose(nameUserLogged) }
            composable(ScreenManager.Viagens.route) { ViagensCompose(navController = navController, idUserLogged) }
            composable(ScreenManager.Sobre.route) { SobreCompose() }
            formViagemGrap(navController, idUserLogged)
            formDespesaGrap(navController, idUserLogged)
        }
    }
}