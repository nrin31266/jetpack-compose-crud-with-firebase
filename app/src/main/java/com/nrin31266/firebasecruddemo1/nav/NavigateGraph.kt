package com.nrin31266.firebasecruddemo1.nav

import androidx.compose.runtime.Composable

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.nrin31266.firebasecruddemo1.screen.AddDataScreen
import com.nrin31266.firebasecruddemo1.screen.GetDataScreen
import com.nrin31266.firebasecruddemo1.screen.ListDataScreen
import com.nrin31266.firebasecruddemo1.screen.MainScreen
import com.nrin31266.firebasecruddemo1.util.SharedViewModel

@Composable
fun NavigateGraph(
    navController: NavHostController,
    sharedViewModel: SharedViewModel
) {
    NavHost(navController = navController, startDestination = Screens.MainScreen.route) {
        //main screen
        composable(
            route = Screens.MainScreen.route
        ) {
            MainScreen(navController = navController)
        }
        //get data screen
        composable(
            route = Screens.GetDataScreen.route
        ) {
            GetDataScreen(navController = navController, sharedViewModel = sharedViewModel)
        }
        //add data screen
        composable(
            route = Screens.AddDataScreen.route
        ) {
            AddDataScreen(navController = navController, sharedViewModel = sharedViewModel)
        }
        composable(
            route = Screens.ListDataScreen.route
        ){
            ListDataScreen(navController=navController, sharedViewModel = sharedViewModel)
        }
    }
}