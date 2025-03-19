package com.example.resturantschadular.model


data class Meal(
    val name: String,
    val prepTime: Int,  // Time required to prepare
    val priority: Int,
    val arrivalTime: Long,
    var startTime: Long = 0L,  // When meal preparation starts
    var servedTime: Long = 0L, // When the meal is actually served
    val icon: Int,
    val caption: String,
)
