package com.example.kontrolajpc.ui.theme.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.LocalTextInputService
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kontrolajpc.ui.theme.KontrolaJPCTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyScreen() {
    var isButtonVisible by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // TopAppBar with some content
        val topAppBarState = rememberTopAppBarState()
        TopAppBar(
            title = { /* your title here */ },
            navigationIcon = { /* your navigation icon here */ },
            actions = {
                if (isButtonVisible) {
                    // Button that can be shown or hidden
                    Button(onClick = { /* handle button click */ }) {
                        // Button content
                    }
                }
            },
            /*elevation = 8.dp,
            topAppBarState = topAppBarState*/
        )

        // Other content of the screen

        // Toggle button visibility
        Button(onClick = { isButtonVisible = !isButtonVisible }) {
            Text(text = if (isButtonVisible) "Hide Button" else "Show Button")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MyScreenPreview() {
    KontrolaJPCTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            MyScreen()
        }
    }
}