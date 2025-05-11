package com.tafreshiali.numera

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.tafreshiali.numera.presentation.CalculatorMainScreen
import com.tafreshiali.numera.presentation.theme.design_sytem.NumeraAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NumeraAppTheme {
                CalculatorMainScreen()
            }
        }
    }
}
