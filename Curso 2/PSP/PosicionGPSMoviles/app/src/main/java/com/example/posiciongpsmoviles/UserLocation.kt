package com.example.posiciongpsmoviles

data class UserLocation(
    val userId: String = "",
    val name: String = "",
    val lat: Double = 0.0,
    val lon: Double = 0.0,
    val timestamp: Long = System.currentTimeMillis()
)
