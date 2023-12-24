package com.example.kontrolajpc.presentation.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.kontrolajpc.navigation.Screen
import com.example.kontrolajpc.useCase.FaultEvent
import com.example.kontrolajpc.presentation.FaultState

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
            TopAppBar(title = { Text("Doma") })
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
        FaultList(paddingValues)

    }


}

@Composable
fun FaultList(padding: PaddingValues) {
    LazyColumn(
        modifier = Modifier.padding(padding)
    ) {

        items(count = 145) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    modifier = Modifier
                        .weight(1f),
                    text = "datum: $it"
                )
                Text(
                    modifier = Modifier
                        .weight(1f),
                    text = "vrsta napake: $it"
                )
                Text(
                    modifier = Modifier
                        .weight(1f)
                        .wrapContentWidth(Alignment.End)
                        .padding(end = 8.dp),
                    text = "exportano: $it"
                )
            }
        }
    }
}

/*
@Composable
@Preview(showBackground = true)
fun HomeScreenPreview() {
    HomeScreen(
        navController = rememberNavController()
    )
}
*/
