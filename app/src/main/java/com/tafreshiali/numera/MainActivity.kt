package com.tafreshiali.numera

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.tafreshiali.numera.presentation.CalculatorMainScreen
import com.tafreshiali.numera.presentation.theme.design_sytem.NumeraAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setContent {
            var darkTheme by remember { mutableStateOf<Boolean?>(null) }
            NumeraAppTheme(isDarkTheme = darkTheme) {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CalculatorMainScreen(
                        modifier = Modifier.padding(innerPadding),
                        isDarkTheme = darkTheme ?: isSystemInDarkTheme(),
                        updateTheme = {
                            darkTheme = it
                        },
                    )
                }
            }
        }
    }
}
