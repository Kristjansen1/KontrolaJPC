package com.example.kontrolajpc.presentation.home

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.kontrolajpc.ElectricalFaults
import com.example.kontrolajpc.navigation.Screen
import com.example.kontrolajpc.useCase.FaultEvent
import com.example.kontrolajpc.presentation.FaultState
import com.example.kontrolajpc.presentation.home.components.FaultCard
import com.example.kontrolajpc.util.DateUtil

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
                Icon(Icons.Filled.Add, "Floating action button.")
            }
        }
    ) { paddingValues ->
        //Test()
        FaultList(paddingValues, state,onEvent)

    }


}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FaultList(
    padding: PaddingValues,
    state: FaultState,
    onEvent: (FaultEvent) -> Unit,
) {
    LazyColumn(
        modifier = Modifier.padding(padding)
    ) {
        val groupedByDate = state.FaultList.groupBy { DateUtil.fromLongToDate(it.datum) }
        groupedByDate.forEach { (header, list) ->
            Log.d("neke header", header)
            list.forEach {
                Log.d("neke list", it.datum.toString())
            }
        }

            groupedByDate.forEach { (header, fault) ->
                stickyHeader {
                    Text(
                        text = header,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        fontWeight = FontWeight.Bold,

                    )
                }
                items(
                    items = fault,
                    key = {
                        fault -> fault.id
                    }
                ) {fault ->
                    FaultCard(
                        fault,
                        onClick = {
                            onEvent(FaultEvent.DeleteFault(it))
                        }
                    )
                }
            }
    }
}

@Composable
@Preview(showBackground = true)
fun HomeScreenPreview() {
    HomeScreen(
        navController = rememberNavController(),
        onEvent = {},
        state = FaultState()
    )
}
