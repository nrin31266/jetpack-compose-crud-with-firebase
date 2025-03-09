package com.nrin31266.firebasecruddemo1.util

import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.util.UUID

class SharedViewModel : ViewModel() {
    fun saveData(userData: UserData, context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            val fireStoreRef = Firebase.firestore.collection("user").document(userData.userId)

            try {
                val snapshot = fireStoreRef.get().await();
                if(snapshot.exists()){
                    withContext(Dispatchers.Main) {
                        Toast.makeText(context, "User already existed with ID: "+ userData.userId, Toast.LENGTH_SHORT).show()
                    }
                    return@launch
                }

                val firebaseStorageRef = FirebaseStorage.getInstance().reference.child("/images/${UUID.randomUUID()}")

                fireStoreRef.set(userData).await()
                withContext(Dispatchers.Main) {
                    Toast.makeText(context, "Successfully saved data", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    fun updateUser(userId: String, userData: UserData, context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            val fireStoreRef = Firebase.firestore.collection("user").document(userId)

            try {
                fireStoreRef.set(userData).await()
                withContext(Dispatchers.Main) {
                    Toast.makeText(context, "Successfully saved data", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun retrieveData(userId: String, context: Context, data: (data: UserData) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            val fireStoreRef = Firebase.firestore.collection("user").document(userId)

            try {
                val snapshot = fireStoreRef.get().await()
                if (snapshot.exists()) {
                    val userData = snapshot.toObject(UserData::class.java)!!
                    withContext(Dispatchers.Main) { data(userData) }
                } else {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(context, "No User Data Found", Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun deleteUserData(
        userId: String,
        context: Context,
        backToMainScreen: () -> Unit,
        navController: NavController
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            val fireStoreRef = Firebase.firestore.collection("user").document(userId)

            try {
                fireStoreRef.delete().await()

                // Chuyển về Main Thread để navigate
                withContext(Dispatchers.Main) {
                    navController.popBackStack()
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun getAllUser(context: Context, onResult: (List<UserData>) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            val firebaseRef = Firebase.firestore.collection("user");

            try {
                val snapshot = firebaseRef.get().await()
                val users = snapshot.documents.mapNotNull { it.toObject(UserData::class.java) }

                withContext(Dispatchers.Main) {
                    onResult(users)
                }

            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    suspend fun uploadImage(context: Context, imageUri: Uri): String? {
        return withContext(Dispatchers.IO) {
            try {
                val storageRef = FirebaseStorage.getInstance().reference.child("images/${UUID.randomUUID()}.jpg")
                storageRef.putFile(imageUri).await()
                storageRef.downloadUrl.await().toString()
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(context, "Upload failed: ${e.message}", Toast.LENGTH_SHORT).show()
                }
                null
            }
        }
    }


}