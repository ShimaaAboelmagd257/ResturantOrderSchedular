package com.example.resturantschadular.model

data class Meal(
    val name: String,
    val arrivalTime: Long,
    val prepTime: Int,
    val priority: Int,
    val icon: Int,
    val caption: String,
    var servedTime: Long = 0L,
    var startTime: Long = 0L

)
