package com.example.demologin.request

data class LoginRequest(
    val email: String,
    val password: String,
    val user_type: String
)