package com.example.kontrolajpc.presentation.addFault.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.kontrolajpc.ElectricalFaults
import com.example.kontrolajpc.presentation.FaultState
import com.example.kontrolajpc.useCase.FaultEvent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FaultDropDown(
    state: FaultState,
    onEvent: (FaultEvent) -> Unit
) {
    var isExpanded by remember {
        mutableStateOf(false)
    }
    ExposedDropdownMenuBox(modifier = Modifier.padding(start = 20.dp, top = 20.dp),
        expanded = isExpanded,
        onExpandedChange = {
            isExpanded = it
        }) {
        TextField(
            value = ElectricalFaults.values()[state.vrstaNapake].toString(),
            label = {
                Text("Vrsta napake")
            },
            onValueChange = {
            },
            readOnly = true,
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors(),
            modifier = Modifier.menuAnchor()
        )
        ExposedDropdownMenu(
            expanded = isExpanded,
            onDismissRequest = {
                isExpanded = false
            }
        ) {
            ElectricalFaults.values().forEach {
                DropdownMenuItem(text = {
                    Text(it.name)
                }, onClick = {
                    isExpanded = false
                    onEvent(
                        FaultEvent.SetVrstaNapake(
                            ElectricalFaults.getPositionByName(it.name)
                        )
                    )
                })
            }

        }

    }
}