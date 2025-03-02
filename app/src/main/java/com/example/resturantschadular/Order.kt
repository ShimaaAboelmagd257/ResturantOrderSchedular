package com.example.resturantschadular

data class Order (
    val name: String,
    val prepTime: Int, // Minutes
    val priority: Int, // 1 (low) to 5 (high)
    val arrivalTime: Int // Timestamp in minutes
)