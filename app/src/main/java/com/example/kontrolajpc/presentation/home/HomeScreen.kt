package com.example.kontrolajpc.presentation.home

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.kontrolajpc.R
import com.example.kontrolajpc.applog
import com.example.kontrolajpc.navigation.Screen
import com.example.kontrolajpc.presentation.FaultState
import com.example.kontrolajpc.presentation.SelectionState
import com.example.kontrolajpc.presentation.home.components.FaultList
import com.example.kontrolajpc.presentation.settings.SettingsViewmodel
import com.example.kontrolajpc.useCase.FaultEvent
import com.example.kontrolajpc.useCase.OnSelection


@RequiresApi(Build.VERSION_CODES.Q)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun HomeScreen(
    navController: NavHostController,
    state: FaultState,
    onEvent: (FaultEvent) -> Unit,
    onSelection: (OnSelection) -> Unit,
    selectionState: SelectionState
) {

    var moreMenuExpanded by rememberSaveable {
        mutableStateOf(false)
    }
    Scaffold(
        topBar = {
            MediumTopAppBar(
                title = {
                    Text(
                        text = "Lista napak",
                        fontSize = 30.sp,
                    )
                },

                navigationIcon = {
                    if (!selectionState.enableMultipleSelection) {
                        IconButton(onClick = {}) {
                            Icon(
                                imageVector = Icons.Default.Home,
                                contentDescription = "Home"
                            )
                        }
                    } else {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            IconButton(
                                onClick = {
                                    onSelection(OnSelection.ClearSelection)
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Clear,
                                    contentDescription = "Home",
                                )
                            }
                            Text(
                                text = "Izbira " + selectionState.listOfSelectedFaultIds.size.toString()

                            )
                        }


                    }

                },
                actions = {
                    if (!selectionState.enableMultipleSelection) {
                        IconButton(
                            onClick = {
                                moreMenuExpanded = true
                            },
                        ) {
                            Icon(
                                imageVector = Icons.Default.MoreVert,
                                contentDescription = "Menu",
                            )
                            DropdownMenu(
                                expanded = moreMenuExpanded,
                                onDismissRequest = {
                                    moreMenuExpanded = false
                                }) {
                                DropdownMenuItem(
                                    text = {Text("Nastavitve")},
                                    onClick = {
                                        navController.navigate(Screen.Settings.route)
                                        moreMenuExpanded = false
                                        applog("dmenu","nastavitve")
                                    })
                                DropdownMenuItem(
                                    text = {Text("Izbriši izvoženo")},
                                    onClick = {
                                        moreMenuExpanded = false
                                        onEvent(FaultEvent.DeleteExported)
                                    })
                            }
                        }
                    } else {
                        IconButton(
                            onClick = {
                                onEvent(FaultEvent.BulkDelete(selectionState.listOfSelectedFaultIds))
                            },
                        ) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "Menu",

                                )
                        }
                        IconButton(
                            onClick = {
                                onEvent(FaultEvent.ExportFaults)
                            },
                        ) {
                            Icon(
                                painterResource(R.drawable.export),
                                contentDescription = "Menu",
                            )
                        }
                    }
                }
            )

        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(Screen.AddFault.route)
                },
            ) {
                Icon(
                    Icons.Filled.Add, "Floating action button."
                )
            }
        },
    ) { paddingValues ->
        //Test()
        FaultList(
            paddingValues,
            state,
            onEvent,
            navController,
            onSelection,
            selectionState
        )
    }
}


@RequiresApi(Build.VERSION_CODES.Q)
@Composable
@Preview(showBackground = true)
fun HomeScreenPreview() {
    HomeScreen(
        navController = rememberNavController(),
        state = FaultState(),
        onEvent = {},
        onSelection = {},
        selectionState = SelectionState()
    )
}
