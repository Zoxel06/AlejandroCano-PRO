package com.example.agendajson.model

import java.io.Serializable

data class User(
    val id: Long? = null,
    val firstName: String? = null,
    val lastName: String? = null,
    val maidenName: String? = null,
    val age: Long? = null,
    val gender: String? = null,
    val email: String? = null,
    val phone: String? = null,
    val username: String? = null,
    val password: String? = null,
    val birthDate: String? = null,
    val image: String? = null
) : Serializable