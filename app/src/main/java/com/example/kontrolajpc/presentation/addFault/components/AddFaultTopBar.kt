package com.example.kontrolajpc.presentation.addFault.components

import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.kontrolajpc.useCase.FaultEvent
import com.example.kontrolajpc.util.BackPressHandler

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddFaultTopBar(
    navController: NavController,
    onEvent: (FaultEvent) -> Unit)
{

    TopAppBar(title = {
        Text(
            text = "Dodaj napako",
        )
    },
        navigationIcon = {
            IconButton(onClick = {
                navController.popBackStack()
                onEvent(FaultEvent.ClearState)
            }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Go Back"
                )
            }
        },
        actions = {
            IconButton(
                onClick = {
                    //todo: add check if fields are empty
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = null
                )

            }
        }
    )
}