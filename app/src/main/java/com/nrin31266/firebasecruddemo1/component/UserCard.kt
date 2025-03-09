package com.nrin31266.firebasecruddemo1.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nrin31266.firebasecruddemo1.util.UserData

@Composable
@Preview
fun UserCard(user: UserData = UserData("1", "Admin", "Manager", 18)) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Color.Gray, RoundedCornerShape(12.dp))
            .padding(10.dp)
    ) {
        Text(text = "ID: ${user.userId}")
        Text(text = "Name: ${user.name}")
        Text(text = "Profession: ${user.profession}")
        Text(text = "Age: ${user.age}")
    }
}