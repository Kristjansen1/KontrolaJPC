package com.example.kontrolajpc.ui.theme

sealed class Screen(val route: String) {
    object Home: Screen(route = "home_Screen")
    object AddError: Screen(route = "add_error_screen")
}