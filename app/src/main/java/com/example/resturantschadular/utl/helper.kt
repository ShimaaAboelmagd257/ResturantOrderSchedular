package com.example.resturantschadular.utl

import com.example.resturantschadular.R
import com.example.resturantschadular.model.Meal

fun getMeals(): List<Meal> {
    return listOf(
        Meal(
            name = "Sushi",
            prepTime = 10,
            priority = 4,
            arrivalTime = getCurrentTime(),
            servedTime = getCurrentTime(),
            icon = R.drawable.sushi,
            caption = "Fresh, delicate, and expertly rolled—your perfect bite of Japanese delight!"
        ),
        Meal(
            name = "Burger",
            prepTime = 5,
            priority = 3,
            arrivalTime = getCurrentTime(),
            servedTime = getCurrentTime(),
            icon = R.drawable.burger,
            caption = "Juicy beef, melted cheese, and crispy toppings—every bite is pure satisfaction!"
        ),
        Meal(
            name = "Salad",
            prepTime = 3,
            priority = 5,
            arrivalTime = getCurrentTime(),
            servedTime = getCurrentTime(),
            icon = R.drawable.salad,
            caption = "Light, refreshing, and packed with nutrients—your go-to for a healthy meal!"
        ),
        Meal(
            name = "Steak",
            prepTime = 15,
            priority = 2,
            arrivalTime = getCurrentTime(),
            servedTime = getCurrentTime(),
            icon = R.drawable.steak,
            caption = "Perfectly grilled, rich in flavor, and served just the way you love it!"
        ),
        Meal(
            name = "Pizza",
            prepTime = 12,
            priority = 1,
            arrivalTime = getCurrentTime(),
            servedTime = getCurrentTime(),
            icon = R.drawable.pizza,
            caption = "Golden crust, melted cheese, and your favorite toppings—always a classic!"
        )
    )
}
fun getCurrentTime(): Int {
    return (System.currentTimeMillis() / 60000).toInt()
}