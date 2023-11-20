package com.example.kontrolajpc.ui.theme.composables

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Fab() {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { Log.d("fab", "fab clicked") },
            ) {
                Icon(Icons.Filled.Add, "Floating action button.")
            }
        }
    ) {

    }
}