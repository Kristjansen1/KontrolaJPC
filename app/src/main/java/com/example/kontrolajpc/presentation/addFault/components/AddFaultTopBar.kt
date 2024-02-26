package com.example.kontrolajpc.presentation.addFault.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.kontrolajpc.presentation.FaultState
import com.example.kontrolajpc.useCase.FaultEvent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddFaultTopBar(
    navController: NavController,
    onEvent: (FaultEvent) -> Unit,
    state: FaultState
)
{

    val titleText = if (state.enableEdit) {
        "Uredi napako"
    } else {
        "Dodaj napako"
    }

    //val cboard = ClipboardHelper(LocalContext.current)
    //cboard.getClipboardLastThree()
    MediumTopAppBar(title = {
        Text(
            text = titleText,
            fontSize = 30.sp
        )
    },
        navigationIcon = {
            IconButton(
                onClick = {
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
                    if (state.enableEdit) {
                        onEvent(FaultEvent.SetEnableEdit(false))

                    }
                    onEvent(FaultEvent.SaveFault)
                    onEvent(FaultEvent.ClearState)
                    navController.popBackStack()

                    //todo check for empty fields
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