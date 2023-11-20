package com.example.kontrolajpc.ui.theme

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.kontrolajpc.ui.theme.screens.AddErrorScreen
import com.example.kontrolajpc.ui.theme.screens.HomeScreen

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(
            route = Screen.Home.route
        ) {
            HomeScreen(navController)
        }
        composable(
            route = Screen.AddError.route
        ) {
            AddErrorScreen(navController)
        }
    }
}
