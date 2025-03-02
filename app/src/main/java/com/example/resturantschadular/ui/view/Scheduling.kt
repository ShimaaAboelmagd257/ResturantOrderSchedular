package com.example.resturantschadular.ui.view

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.resturantschadular.R
import com.example.resturantschadular.model.Meal

@Preview
@Composable
private fun prview() {
    MenuOrder()
}
@Composable
fun MenuOrder() {

    val meals = listOf(
        Meal("Burger", R.drawable.burger),
        Meal("Pizza", R.drawable.pizza),
        Meal("Sushi" , R.drawable.sushi),
        Meal("Steak",R.drawable.steak),
        Meal("Salad", R.drawable.salad)
    )

    val selectedMeals = remember { mutableStateListOf<Meal>() }
    var selectedAlgorithm by remember { mutableStateOf("Round Robin") }
    val algorithmOptions = listOf("Round Robin", "FCFS" , "sjN" , "Priority Scheduling")

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp).background(color = Color.Magenta)
    ){
        Text(text = "Order Your Meals", fontSize = 30.sp , fontWeight = FontWeight.Bold, color = Color.White, modifier = Modifier.padding(horizontal = 20.dp))

        LazyColumn {
            items(meals){ meal ->
                MealCard(meal,selectedMeals)
            }
        }
    }
}

@Composable
fun MealCard(meal:Meal, selectedMeals:MutableList<Meal>) {
    var isSelected by remember { mutableStateOf(false) }
    Card (modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)
        .clickable {
            isSelected = !isSelected
            if (isSelected) selectedMeals.add(meal) else selectedMeals.remove(meal)
        },
        shape = RoundedCornerShape(35.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ){
        Row (
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(20.dp)
        ){
            Image(painter = painterResource(id= meal.icon), contentDescription = meal.name, modifier = Modifier.size(100.dp))
            Spacer(modifier = Modifier.width(20.dp))
            Text(text = meal.name, fontSize = 25.sp , fontWeight = FontWeight.Medium)
            Spacer(modifier = Modifier.width(60.dp))
            Checkbox(
                checked = isSelected,
                onCheckedChange = {
                    isSelected = it
                    if(it) selectedMeals.add(meal) else selectedMeals.remove(meal)
                }
            )

        }

    }

}