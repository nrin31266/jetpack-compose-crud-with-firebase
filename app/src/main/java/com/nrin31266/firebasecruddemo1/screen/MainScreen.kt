package com.nrin31266.firebasecruddemo1.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.nrin31266.firebasecruddemo1.nav.Screens
import com.nrin31266.firebasecruddemo1.util.SharedViewModel

@Composable
fun MainScreen(
    navController: NavController,
) {
    Column(
        modifier = Modifier
            .padding(start = 50.dp, end = 50.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //get user data button
        Button(onClick = { navController.navigate(Screens.GetDataScreen.route) },
            modifier = Modifier.fillMaxWidth()) {
            Text(text = "Get user data")
        }
        //list data
        Button(onClick = { navController.navigate(Screens.ListDataScreen.route) },
            modifier = Modifier.fillMaxWidth()) {
            Text(text = "List data")
        }
        //add user data button
        OutlinedButton(onClick = {navController.navigate(Screens.AddDataScreen.route)},
            modifier = Modifier.fillMaxWidth()) {
            Text(text = "Add user data")
        }
    }
}