package com.nrin31266.firebasecruddemo1.nav

sealed class Screens(val route: String){
    object MainScreen: Screens(route = "main_screen")
    object GetDataScreen: Screens(route = "get_data_screen")
    object AddDataScreen: Screens(route = "add_data_screen")
    object ListDataScreen: Screens(route = "list_data_screen")
}