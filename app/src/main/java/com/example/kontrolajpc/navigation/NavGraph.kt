package com.example.kontrolajpc.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.kontrolajpc.presentation.addFault.AddErrorScreen
import com.example.kontrolajpc.useCase.FaultEvent
import com.example.kontrolajpc.presentation.FaultState
import com.example.kontrolajpc.presentation.home.HomeScreen

@Composable
fun SetupNavGraph(
    navController: NavHostController,
    state: FaultState,
    onEvent: (FaultEvent) -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        enterTransition = {
            EnterTransition.None
        },
        exitTransition = {
            ExitTransition.None
        }
    ) {
        composable(
            route = Screen.Home.route
        ) {
            HomeScreen(
                navController,
                state,
                onEvent
            )
        }
        composable(
            route = Screen.AddFault.route
        ) {
            AddErrorScreen(
                navController,
                state,
                onEvent
            )
        }
    }
}
