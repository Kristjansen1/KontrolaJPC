package com.example.kontrolajpc.ui.theme.navigation

sealed class Screen(val route: String) {
    object Home: Screen(route = "home_Screen")
    object AddFault: Screen(route = "add_fault_screen")
}