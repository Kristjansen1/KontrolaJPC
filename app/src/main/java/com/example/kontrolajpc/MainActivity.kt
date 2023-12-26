package com.example.kontrolajpc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.kontrolajpc.navigation.SetupNavGraph
import com.example.kontrolajpc.ui.theme.KontrolaJPCTheme

class MainActivity : ComponentActivity() {

    private val viewModel: ViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        //enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            KontrolaJPCTheme {
                val state by viewModel.state.collectAsState()
                val navController = rememberNavController()
                SetupNavGraph(
                    navController = navController,
                    state,
                    viewModel::onEvent
                )

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    KontrolaJPCTheme {
    }
}