package com.example.kontrolajpc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.kontrolajpc.ui.theme.KontrolaJPCTheme
import com.example.kontrolajpc.ui.theme.navigation.SetupNavGraph

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KontrolaJPCTheme {

                val navController = rememberNavController()
                SetupNavGraph(navController = navController)

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