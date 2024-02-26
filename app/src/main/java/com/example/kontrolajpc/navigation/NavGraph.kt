package com.example.kontrolajpc.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.kontrolajpc.ViewModel
import com.example.kontrolajpc.presentation.addFault.AddErrorScreen
import com.example.kontrolajpc.presentation.home.HomeScreen
import com.example.kontrolajpc.presentation.settings.SettingsScreen

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun SetupNavGraph (
    navController: NavHostController,
    viewModel: ViewModel,
) {
    val state by viewModel.state.collectAsState()
    val selectionState by viewModel.selectionState.collectAsState()


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
        composable(route = Screen.Home.route) {
            HomeScreen(
                navController,
                state,
                viewModel::onEvent,
                viewModel::onSelection,
                selectionState
            )
        }
        composable(route = Screen.AddFault.route) {
            AddErrorScreen(
                navController,
                state,
                viewModel::onEvent,
            )
        }
        composable(route = Screen.Settings.route) {
            SettingsScreen(
                navController,
            )
        }
    }
}
