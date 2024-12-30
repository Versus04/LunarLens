package com.example.lunarlens.utils

sealed class ScreenList(val route : String) {
    object HomeScreen : ScreenList("home_screen")
    object SearchScreen : ScreenList("search_screen")
    object MarsScreen : ScreenList("mars_screen")

}