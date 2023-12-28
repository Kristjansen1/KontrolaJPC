package com.example.kontrolajpc.presentation.home

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.kontrolajpc.navigation.Screen
import com.example.kontrolajpc.presentation.FaultState
import com.example.kontrolajpc.presentation.home.components.FaultList
import com.example.kontrolajpc.useCase.FaultEvent

@RequiresApi(Build.VERSION_CODES.Q)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavHostController,
    state: FaultState,
    onEvent: (FaultEvent) -> Unit
) {
    Scaffold(
        topBar = {
            MediumTopAppBar(
                title = {
                    Text(
                        //modifier = Modifier.padding(10.dp),
                        text = "Lista napak",
                        fontSize = 30.sp,
                        //fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Default.Home,
                            contentDescription = "Home"
                        )
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
        }
    ) { paddingValues ->
        //Test()
        FaultList(paddingValues, state, onEvent)
    }
}



@RequiresApi(Build.VERSION_CODES.Q)
@Composable
@Preview(showBackground = true)
fun HomeScreenPreview() {
    HomeScreen(
        navController = rememberNavController(),
        onEvent = {},
        state = FaultState()
    )
}
