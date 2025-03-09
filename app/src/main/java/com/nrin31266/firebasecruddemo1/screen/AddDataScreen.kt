package com.nrin31266.firebasecruddemo1.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.nrin31266.firebasecruddemo1.util.SharedViewModel
import com.nrin31266.firebasecruddemo1.util.UserData
import kotlin.reflect.typeOf


@Composable
fun AddDataScreen(
    navController: NavController,
    sharedViewModel: SharedViewModel
) {

    var userId: String by remember { mutableStateOf("") }
    var name: String by remember { mutableStateOf("") }
    var profession: String by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }

    var context = LocalContext.current

    // main layout

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
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

        //
        Column(
            modifier = Modifier
                .padding(start = 60.dp, end = 60.dp, bottom = 40.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // user ID
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = userId,
                onValueChange = {
                    userId = it
                },
                label = {
                    Text("User ID")
                }
            )
            // name
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = name,
                onValueChange = {
                    name = it
                },
                label = {
                    Text("Name")
                }
            )
            // Profession
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = profession,
                onValueChange = {
                    profession = it
                },
                label = {
                    Text("Profession")
                }
            )
            // Age
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = age,
                onValueChange = {
                    age = it


                },
                label = {
                    Text("Age")
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            //save button
            Button(
                onClick = {
                    var userData = UserData(
                        userId = userId,
                        name = name,
                        profession = profession,
                        age = age.toInt()
                    )

                    sharedViewModel.saveData(userData, context);
                },
                modifier = Modifier.fillMaxWidth().padding(top = 50.dp)
            ) {
                Text("Add")
            }
        }
    }

}