package com.example.kontrolajpc.presentation.addFault

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.kontrolajpc.useCase.FaultEvent
import com.example.kontrolajpc.presentation.FaultState
import com.example.kontrolajpc.presentation.addFault.components.AddFaultTopBar
import com.example.kontrolajpc.presentation.addFault.components.FaultDropDown
import com.example.kontrolajpc.presentation.addFault.components.PickDate
import com.example.kontrolajpc.util.BackPressHandler
import com.example.kontrolajpc.util.DateUtil

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddErrorScreen(
    navController: NavHostController,
    state: FaultState,
    onEvent: (FaultEvent) -> Unit
) {
    var charCount by rememberSaveable {
        mutableStateOf("0/50")
    }
    BackPressHandler {
        navController.popBackStack()
        onEvent(FaultEvent.ClearState)
    }
    Scaffold(
        topBar = {
            AddFaultTopBar(
                navController,
                onEvent,
                state
            )
        }) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(paddingValues)
        ) {
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(
                readOnly = true,
                modifier = Modifier
                    .padding(start = 20.dp, end = 20.dp, bottom = 10.dp)
                    .fillMaxWidth(),
                value = DateUtil.fromLongToDate(state.date),
                onValueChange = {},
                trailingIcon = {
                    Icon(imageVector = Icons.Default.DateRange,
                        contentDescription = "Izberi datum",
                        Modifier.clickable(
                            onClick = {
                                //dateDialogShowState = true
                                onEvent(FaultEvent.SetDateDialogShowState(true))
                            }
                        ))
                }
            )

            Tfield(label = "Posel", value = state.posel, onValueChange = {
                onEvent(FaultEvent.SetPosel(it))
            })

            Tfield(label = "Serijska", value = state.serijska, onValueChange = {
                onEvent(FaultEvent.SetSeriska(it))
            })
            Tfield(label = "Proizvodni nalog", value = state.proizvodnjiNalog, onValueChange = {
                onEvent(FaultEvent.SetProizvodniNalog(it))
                Log.d("newvalue", it)
            })
            FaultDropDown(state,onEvent)
            Row(
                modifier = Modifier.padding(start = 20.dp, top = 20.dp),
            ) {
                Text(
                    modifier = Modifier.weight(5f),
                    text = "Opis napake:",
                    fontWeight = FontWeight.Bold
                )
                Text(
                    modifier = Modifier.weight(1f),
                    text = charCount,
                    //fontWeight = FontWeight.Bold
                )
            }
            TextField(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(all = 20.dp),
                value = state.opisNapake,
                onValueChange = {
                    val c = it.count()
                    if (c <= 50) {
                        charCount = "$c/50"
                        onEvent(FaultEvent.SetOpisNapake(it))
                    }

                })
                PickDate(onEvent, state)
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Tfield(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    trailingIcon: @Composable (() -> Unit)? = null
) {
    OutlinedTextField(
        modifier = Modifier
            .padding(start = 20.dp, end = 20.dp, bottom = 10.dp)
            .fillMaxWidth(),
        value = value,
        singleLine = true,
        onValueChange = onValueChange,
        label = {
            Text(label)
        },
        trailingIcon = trailingIcon
    )
}
