package com.example.resturantschadular.model

data class Meal(
    val name: String,
    val prepTime: Int, // Minutes required to prepare the meal
    val priority: Int, // 1 (low) to 5 (high)
    val arrivalTime: Int, // Time when order was placed
    val icon: Int // Icon resource ID
)