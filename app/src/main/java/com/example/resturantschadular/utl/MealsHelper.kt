package com.example.resturantschadular.utl

import com.example.resturantschadular.R
import com.example.resturantschadular.model.Meal
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


fun getMeals(): List<Meal> {
    val baseTime = System.currentTimeMillis()

    return listOf(
        Meal(
            name = "Sushi",
            prepTime = 10,
            priority = 4,
            icon = R.drawable.sushi,
            caption = "Fresh and delicate.",
            arrivalTime = baseTime
        ),
        Meal(
            name = "Burger",
            prepTime = 5,
            priority = 3,
            icon = R.drawable.burger,
            caption = "Juicy and delicious.",
            arrivalTime = baseTime + 1000
        ), // ✅ Arrives 1 sec later
        Meal(
            name = "Salad",
            prepTime = 3,
            priority = 5,
            icon = R.drawable.salad,
            caption = "Light and healthy.",
            arrivalTime = baseTime + 2000
        ), // ✅ Arrives 2 sec later
        Meal(
            name = "Steak",
            prepTime = 15,
            priority = 2,
            icon = R.drawable.steak,
            caption = "Perfectly grilled.",
            arrivalTime = baseTime + 3000
        ),
        Meal(
            name = "Pizza",
            prepTime = 12,
            priority = 1,
            icon = R.drawable.pizza,
            caption = "Crispy and cheesy.",
            arrivalTime = baseTime + 4000
        )
    )
}
fun formatTimestamp(timestamp: Long): String {
    if (timestamp == 0L) return "Not started"
    val sdf = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
    return sdf.format(Date(timestamp))
}
fun getCurrentTime(): Long {
    return (System.currentTimeMillis())
}