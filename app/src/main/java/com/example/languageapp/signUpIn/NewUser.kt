package com.example.languageapp.signUpIn

import kotlinx.serialization.Serializable

@Serializable
data class NewUser (
    val id: Int = 0,
    val firstname: String = "",
    val lastname: String = "",
    val email: String = "",
    val password: String = "",
    val score: Int = 0
)
