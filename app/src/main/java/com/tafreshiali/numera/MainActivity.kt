package com.tafreshiali.numera

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tafreshiali.domain.AppProtoDataStore
import com.tafreshiali.domain.model.AppSettings
import com.tafreshiali.numera.presentation.CalculatorMainScreen
import com.tafreshiali.numera.presentation.theme.design_sytem.NumeraAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var dataStore: AppProtoDataStore<AppSettings>

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        setContent {
            val appSettings by dataStore.getValue()
                .collectAsStateWithLifecycle(initialValue = AppSettings())
            var loadingState by rememberSaveable {
                mutableStateOf(true)
            }
            NumeraAppTheme(isDarkTheme = appSettings.selectedTheme) {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CalculatorMainScreen(
                        modifier = Modifier.padding(innerPadding),
                        isDarkTheme = appSettings.selectedTheme ?: isSystemInDarkTheme(),
                    )
                }
            }
            LaunchedEffect(Unit) {
                delay(2000)
                loadingState = false
            }
            splashScreen.setKeepOnScreenCondition {
                loadingState
            }
        }
    }
}
