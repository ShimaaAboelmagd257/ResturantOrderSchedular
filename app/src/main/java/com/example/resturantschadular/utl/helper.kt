package com.example.resturantschadular.utl

import com.example.resturantschadular.R
import com.example.resturantschadular.model.Meal

fun getMeals(): List<Meal> {
    return listOf(
        Meal("Sushi", prepTime = 10, priority = 4, arrivalTime = getCurrentTime(), icon = R.drawable.sushi),
        Meal("Burger", prepTime = 5, priority = 3, arrivalTime = getCurrentTime(), icon = R.drawable.burger),
        Meal("Salad", prepTime = 3, priority = 5, arrivalTime = getCurrentTime(), icon = R.drawable.salad),
        Meal("Steak", prepTime = 15, priority = 2, arrivalTime = getCurrentTime(), icon = R.drawable.steak),
        Meal("Pizza", prepTime = 12, priority = 1, arrivalTime = getCurrentTime(), icon = R.drawable.pizza)
    )
}
fun getCurrentTime(): Int {
    return (System.currentTimeMillis() / 60000).toInt()
}