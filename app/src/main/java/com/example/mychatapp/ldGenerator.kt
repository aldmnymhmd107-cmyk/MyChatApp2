package com.example.mychatapp

import java.util.UUID

object IdGenerator {
    fun generateMyId(): String {
        val randomCode = UUID.randomUUID().toString().take(4).uppercase()
        return "ID-$randomCode"
    }
}