package com.nrin31266.firebasecruddemo1.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.nrin31266.firebasecruddemo1.component.UserCard
import com.nrin31266.firebasecruddemo1.util.SharedViewModel
import com.nrin31266.firebasecruddemo1.util.UserData

@Composable
fun ListDataScreen(
    navController: NavController,
    sharedViewModel: SharedViewModel
) {


    val context = LocalContext.current
    var users by remember { mutableStateOf<List<UserData>>(emptyList()) }
    LaunchedEffect(Unit) {
        sharedViewModel.getAllUser(context) { data ->
            users = data
        }
    }


    // main layout

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        //back button
        Row(
            modifier = Modifier
                .padding(start = 15.dp, end = 15.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "back_button")
            }
        }


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 5.dp, end = 5.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(10.dp),

        ) {
            users.map { user ->
                UserCard(user)
            }
        }


    }
}