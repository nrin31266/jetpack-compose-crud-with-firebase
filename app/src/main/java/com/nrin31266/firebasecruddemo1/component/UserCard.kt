package com.nrin31266.firebasecruddemo1.component

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.nrin31266.firebasecruddemo1.util.SharedViewModel
import com.nrin31266.firebasecruddemo1.util.UserData
import kotlinx.coroutines.launch
import kotlin.math.log

@Composable
fun UserCard(
    user: UserData = UserData(
        "1",
        "Admin",
        "Manager",
        18,
        "https://th.bing.com/th/id/R.2d31b8bc60d9251052a5c2d910a4d15f?rik=00LkgPJjHOoxhw&pid=ImgRaw&r=0"
    ),
    onDelete: (userId: String)-> Unit,
    sharedViewModel: SharedViewModel
) {

    val coroutineScope = rememberCoroutineScope();

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Color.Gray, RoundedCornerShape(12.dp))
            .padding(10.dp)
    ) {
        Box(
            modifier = Modifier.size(100.dp),
            contentAlignment = Alignment.Center
        ) {
            if (user.imageUrl.isNotBlank()) {
                Image(
                    painter = rememberAsyncImagePainter(user.imageUrl),
                    contentDescription = "Remote Image",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            } else {
                Icon(
                    imageVector = Icons.Outlined.AccountBox,
                    contentDescription = "Default Icon",
                    modifier = Modifier.fillMaxSize()
                )
            }
        }


        Column(
            modifier = Modifier
                .weight(1f) // Giúp chiếm toàn bộ phần còn lại
                .padding(start = 10.dp)
        ) {
            Text(text = "ID: ${user.userId}")
            Text(text = "Name: ${user.name}")
            Text(text = "Profession: ${user.profession}")
            Text(text = "Age: ${user.age}")
        }

        Box(
            modifier = Modifier,
            contentAlignment = Alignment.TopEnd
        ) {
            Column  (
                modifier = Modifier.width(30.dp)
            ){
                IconButton(onClick = { /* TODO: Xử lý Edit */ }) {
                    Icon(
                        imageVector = Icons.Filled.Edit,
                        contentDescription = "Edit"
                    )
                }
                IconButton(onClick = {
                    Log.d(TAG, "UserCard: On Delete")
                    onDelete(user.userId)

                }) {
                    Icon(
                        imageVector = Icons.Filled.Delete,
                        contentDescription = "Delete",
                        tint = Color.Red
                    )
                }
            }
        }


    }


}