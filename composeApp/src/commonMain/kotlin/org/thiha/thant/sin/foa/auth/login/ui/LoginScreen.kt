package org.thiha.thant.sin.foa.auth.login.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color


@Composable
fun LoginScreen() {
    Scaffold { paddingValues ->
        Box(
            modifier = Modifier.fillMaxSize().background(Color.Red)
                .padding(paddingValues = paddingValues)
        )
    }
}