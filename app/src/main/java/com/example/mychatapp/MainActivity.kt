package com.example.mychatapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mychatapp.ui.theme.MyChatAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyChatAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation()
                }
            }
        }
    }
}

@Composable
fun AppNavigation() {
    var currentScreen by remember { mutableStateOf("home") }
    var friendIdInput by remember { mutableStateOf("") }

    if (currentScreen == "home") {
        HomeScreen(
            friendIdInput = friendIdInput,
            onFriendIdChange = { friendIdInput = it },
            onStartChat = { if (friendIdInput.isNotBlank()) currentScreen = "chat" }
        )
    } else {
        ChatScreen(
            friendId = friendIdInput,
            onBack = { currentScreen = "home" }
        )
    }
}

@Composable
fun HomeScreen(
    friendIdInput: String,
    onFriendIdChange: (String) -> Unit,
    onStartChat: () -> Unit
) {
    val myId by remember { mutableStateOf(IdGenerator.generateMyId()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "مرحباً بك في تطبيق المحادثة", fontSize = 22.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "الرقم الخاص بك هو:", fontSize = 16.sp)

        Spacer(modifier = Modifier.height(8.dp))

        Card(colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)) {
            Text(
                text = myId,
                fontSize = 28.sp,
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier.padding(horizontal = 24.dp, vertical = 12.dp)
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        OutlinedTextField(
            value = friendIdInput,
            onValueChange = onFriendIdChange,
            label = { Text("أدخل ID صديقك") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = onStartChat,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "بدء محادثة جديد عبر ID صديقك")
        }
    }
}