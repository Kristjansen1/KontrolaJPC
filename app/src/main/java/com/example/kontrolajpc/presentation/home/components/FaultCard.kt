package com.example.kontrolajpc.presentation.home.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.kontrolajpc.ElectricalFaults
import com.example.kontrolajpc.database.model.FaultModel
import com.example.kontrolajpc.navigation.Screen
import com.example.kontrolajpc.presentation.FaultState
import com.example.kontrolajpc.presentation.SelectionState
import com.example.kontrolajpc.useCase.FaultEvent
import com.example.kontrolajpc.useCase.OnSelection
import org.apache.poi.ss.usermodel.HorizontalAlignment

@RequiresApi(Build.VERSION_CODES.Q)
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FaultCard(
    fault: FaultModel,
    state: FaultState,
    onEvent: (FaultEvent) -> Unit,
    navController: NavController,
    onSelection: (OnSelection) -> Unit,
    selectionState: SelectionState
) {

    var expanded by rememberSaveable {
        mutableStateOf(false)
    }

    var isDropDownMenuVisible by rememberSaveable {
        mutableStateOf(false)
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp, top = 5.dp, bottom = 5.dp)
            .combinedClickable(
                onClick = {
                    expanded = !expanded
                },
            )
    ) {
        Column {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Column(
                    modifier = Modifier
                        .padding(7.dp)
                        .weight(1f)
                ) {
                    Text(
                        modifier = Modifier.padding(start = 5.dp),
                        text = ElectricalFaults.entries[fault.vrstaNapake!!].toString()
                    )
                    Text(
                        modifier = Modifier.padding(start = 5.dp),
                        text = "Izvoženo: ${fault.exported}",
                        fontStyle = FontStyle.Italic,
                        fontSize = 10.sp
                    )
                }

                if (!selectionState.enableMultipleSelection) {
                    IconButton(
                        modifier = Modifier.wrapContentSize(),
                        onClick = {
                            isDropDownMenuVisible = true
                        },
                    ) {
                        Icon(
                            imageVector = Icons.Default.MoreVert,
                            contentDescription = "Delete fault"
                        )
                        DropdownMenu(
                            expanded = isDropDownMenuVisible,
                            onDismissRequest = { isDropDownMenuVisible = false },

                            ) {
                            DropdownMenuItem(
                                text = {
                                    Text(
                                        textAlign = TextAlign.Center,
                                        text = "Izberi"
                                    )
                                },
                                onClick = {
                                    onSelection(OnSelection.EnableMultipleSelection(true))
                                    onSelection(OnSelection.SetSelected(fault.id))
                                    isDropDownMenuVisible = false
                                },
                            )
                            DropdownMenuItem(
                                text = { Text("Uredi") },
                                onClick = {
                                    onEvent(FaultEvent.SetFault(fault))
                                    onEvent(FaultEvent.SetEnableEdit(true))
                                    navController.navigate(Screen.AddFault.route)
                                    isDropDownMenuVisible = false
                                },
                            )
                            DropdownMenuItem(
                                text = { Text("Odstrani") },
                                onClick = {
                                    onEvent(FaultEvent.DeleteFault(fault))
                                },
                            )
                        }
                    }
                } else {
                    Checkbox(
                        checked = selectionState.listOfSelectedFaultIds.contains(fault.id),
                        onCheckedChange = {
                            onSelection(OnSelection.SetSelected(fault.id))
                        },

                        )
                }
            }
            Row {
                Column(
                    modifier = Modifier.padding(start = 10.dp, end = 10.dp)
                ) {
                    if (expanded) {
                        Spacer(modifier = Modifier.height(10.dp))
                        ExpandedData(title = "Posel:", description = fault.posel.toString())
                        ExpandedData(title = "Serijska:", description = fault.serijska.toString())
                        ExpandedData(
                            title = "P. Nalog:",
                            description = fault.proizvodniNalog.toString()
                        )
                        ExpandedData(title = "Opomba:", description = fault.opisNapake.toString())
                        Spacer(modifier = Modifier.height(7.dp))
                    }
                }
            }
        }

    }
}

@Composable
fun ExpandedData(title: String, description: String) {
    Row(
        modifier = Modifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            modifier = Modifier
                .padding(start = 14.dp)
                .weight(1f),
            text = title,
            fontSize = 13.sp
        )
        Text(
            modifier = Modifier.weight(3f),
            text = description,
            fontSize = 14.sp

        )
    }

}

@Preview(showSystemUi = true)
@Composable
fun P() {
    val f = FaultModel(
        datum = 1865613516813,
        serijska = "21651",
        posel = "35168",
        proizvodniNalog = "123",
        exported = false,
        vrstaNapake = 0,
        opisNapake = "neki"
    )
}