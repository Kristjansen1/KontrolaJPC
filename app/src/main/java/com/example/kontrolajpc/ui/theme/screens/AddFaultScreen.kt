package com.example.kontrolajpc.ui.theme.screens

import Test
import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.kontrolajpc.ElectricalFaults
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.util.Calendar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddErrorScreen(
    navController: NavController
) {
    var datum by rememberSaveable {
        mutableStateOf(LocalDate.now().toString())
    }
    var posel by rememberSaveable {
        mutableStateOf("")
    }
    var serijska by rememberSaveable {
        mutableStateOf("")
    }
    var proizvodniNalog by rememberSaveable {
        mutableStateOf("")
    }
    var faultDescription by rememberSaveable {
        mutableStateOf("")
    }
    var charCount by rememberSaveable {
        mutableStateOf("0/50")
    }
    var dateDialogShowState by remember {
        mutableStateOf(false)
    }
    var pickedDate by rememberSaveable {
        mutableStateOf(LocalDate.now().toString())
    }

    Scaffold(
        topBar = {
            AddFaultTopBar(navController)
        }) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(paddingValues)
        ) {

            Tfield(label = "Datum", value = pickedDate, onValueChange = {
                datum = it
            },
                trailingIcon = {
                    Icon(imageVector = Icons.Default.DateRange,
                        contentDescription = "Izberi datum",
                        Modifier.clickable(
                            onClick = {
                                dateDialogShowState = true
                            }
                        ))
                })

            Tfield(label = "Posel", value = posel, onValueChange = {
                posel = it
            })

            Tfield(label = "Serijska", value = serijska, onValueChange = {
                serijska = it
            })
            Tfield(label = "Proizvodni nalog", value = proizvodniNalog, onValueChange = {
                proizvodniNalog = it
                Log.d("newvalue", it)
            })
            FaultDropDown()
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
                value = faultDescription,
                onValueChange = {
                    val c = it.count()
                    if (c <= 50) {
                        charCount = "$c/50"
                        faultDescription = it
                    }

                })
            // Decoupled snackbar host state from scaffold state for demo purposes.
            val snackState = remember { SnackbarHostState() }
            val snackScope = rememberCoroutineScope()
            SnackbarHost(hostState = snackState)
// TODO demo how to read the selected date from the state.
            if (dateDialogShowState) {
                Log.d("neki", "klikna")
                val datePickerState = rememberDatePickerState()
                val confirmEnabled = remember {
                    derivedStateOf { datePickerState.selectedDateMillis != null }
                }
                DatePickerDialog(
                    onDismissRequest = {
                        // Dismiss the dialog when the user clicks outside the dialog or on the back
                        // button. If you want to disable that functionality, simply use an empty
                        // onDismissRequest.
                        dateDialogShowState = false
                    },
                    confirmButton = {
                        TextButton(
                            onClick = {
                                dateDialogShowState = false
                                snackScope.launch {
                                    snackState.showSnackbar(
                                        "Selected date timestamp: ${datePickerState.selectedDateMillis}"
                                    )
                                }
                            },
                            enabled = confirmEnabled.value
                        ) {
                            Text("OK")
                        }
                    },
                    dismissButton = {
                        TextButton(
                            onClick = {
                                dateDialogShowState = false
                            }
                        ) {
                            Text("Cancel")
                        }
                    }
                ) {
                    DatePicker(state = datePickerState)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddFaultTopBar(navController: NavController) {
    TopAppBar(title = {
        Text(
            text = "Dodaj napako",
        )
    },
        navigationIcon = {
            IconButton(onClick = {
                navController.popBackStack()
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FaultDropDown() {
    var isExpanded by remember {
        mutableStateOf(false)
    }
    var fault by remember {
        mutableStateOf("")
    }

    ExposedDropdownMenuBox(modifier = Modifier.padding(start = 20.dp, top = 20.dp),
        expanded = isExpanded,
        onExpandedChange = {
            isExpanded = it
        }) {
        TextField(
            value = fault,
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
        ExposedDropdownMenu(expanded = isExpanded, onDismissRequest = { isExpanded = false }) {
            ElectricalFaults.values().forEach {
                DropdownMenuItem(text = {
                    Text(it.name)
                }, onClick = {
                    isExpanded = false
                    fault = it.name
                    Log.d("exposedmenu", ElectricalFaults.getPositionByName(it.name).toString())
                })
            }

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

@Composable
@Preview(showBackground = true)
fun AddErrorScreenPreview() {
    AddErrorScreen(
        navController = rememberNavController()
    )
}
