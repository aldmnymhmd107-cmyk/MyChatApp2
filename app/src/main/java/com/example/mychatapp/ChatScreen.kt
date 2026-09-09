package com.example.mychatapp

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class Message(val text: String, val isMe: Boolean)

@Composable
fun ChatScreen(friendId: String, onBack: () -> Unit) {
    var messageText by remember { mutableStateOf("") }
    val messages = remember { mutableStateListOf<Message>() }

    Column(modifier = Modifier.fillMaxSize()) {
        // الشريط العلوي
        Surface(shadowElevation = 4.dp, color = MaterialTheme.colorScheme.primary) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(onClick = onBack) { Text("< رجوع") }
                Spacer(modifier = Modifier.width(12.dp))
                Text(text = "محادثة مع: $friendId", color = Color.White, fontSize = 18.sp)
            }
        }

        // قائمة الرسائل
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            items(messages) { msg ->
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = if (msg.isMe) Alignment.CenterEnd else Alignment.CenterStart
                ) {
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = if (msg.isMe) Color(0xFFDCF8C6) else Color(0xFFE8E8E8)
                        ),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.padding(vertical = 4.dp)
                    ) {
                        Text(
                            text = msg.text,
                            color = Color.Black,
                            modifier = Modifier.padding(10.dp)
                        )
                    }
                }
            }
        }

        // مربع إرسال الرسالة
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = messageText,
                onValueChange = { messageText = it },
                placeholder = { Text("اكتب رسالتك...") },
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Button(
                onClick = {
                    if (messageText.isNotBlank()) {
                        messages.add(Message(messageText, isMe = true))
                        messageText = ""
                    }
                }
            ) {
                Text("إرسال")
            }
        }
    }
}

