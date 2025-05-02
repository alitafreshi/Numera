package com.tafreshiali.numera.presentation.components

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive

@Composable
fun CalculatorDeleteActionButtonComponent(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
    onDeleteClick: () -> Unit
) {
    var isDeleting by remember { mutableStateOf(false) }

    LaunchedEffect(isDeleting) {
        if (isDeleting) {
            onDeleteClick()
            delay(300L)

            while (coroutineContext.isActive) {
                onDeleteClick()
                delay(60L)
            }
        }
    }
    Box(
        modifier = modifier
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = {
                        onDeleteClick()
                    },
                    onPress = {
                        isDeleting = true
                        tryAwaitRelease()
                        isDeleting = false
                    }
                )
            },
        contentAlignment = Alignment.Center
    ) {
        content()
    }
}