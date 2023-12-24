package com.example.kontrolajpc.presentation.addFault.components

import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import com.example.kontrolajpc.database.DateConverter1
import com.example.kontrolajpc.presentation.FaultState
import com.example.kontrolajpc.useCase.FaultEvent
import com.example.kontrolajpc.util.DateFormat

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PickDate(
    onEvent: (FaultEvent) -> Unit,
    state: FaultState
) {
    if (state.dateDialogShowState) {
        val datePickerState =
            rememberDatePickerState(initialDisplayMode = DisplayMode.Picker)
        val confirmEnabled = remember {
            derivedStateOf { datePickerState.selectedDateMillis != null }
        }
        DatePickerDialog(

            onDismissRequest = {
                onEvent(FaultEvent.SetDateDialogShowState(false))

            },
            confirmButton = {
                TextButton(
                    onClick = {
                        onEvent(FaultEvent.SetDateDialogShowState(false))
                        onEvent(
                            FaultEvent.SetDate(
                                DateFormat.dateToFormat(
                                    DateConverter1().toDate(datePickerState.selectedDateMillis)!!
                                )
                            )
                        )
                    },
                    enabled = confirmEnabled.value
                ) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        onEvent(FaultEvent.SetDateDialogShowState(false))

                    }
                ) {
                    Text("Cancel")
                }
            }
        ) {
            DatePicker(
                state = datePickerState,
                showModeToggle = false
            )
        }
    }
}