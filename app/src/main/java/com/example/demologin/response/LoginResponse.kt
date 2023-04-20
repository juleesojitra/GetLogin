package com.example.demologin.response

data class LoginResponse(
    val error: String,
    val payload: Payload,
    val status: String
) {
    data class Payload(
        val restaurant_id: String,
        val token: String
    )
}
