package com.example.appviagensfinal.telas

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appviagensfinal.componentes.AppBarTelas

@Composable
fun SobreCompose() {
    Scaffold(
        topBar = { AppBarTelas("Saiba mais") }
    ) {

        Spacer(modifier = Modifier.padding(20.dp))

        Text(
            text = "Sobre",
            fontSize = 35.sp, fontWeight = FontWeight.Bold, fontStyle = FontStyle.Italic
        )
    }
}