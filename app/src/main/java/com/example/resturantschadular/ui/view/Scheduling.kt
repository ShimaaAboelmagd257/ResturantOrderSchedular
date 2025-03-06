package com.example.resturantschadular.ui.view

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.resturantschadular.R
import com.example.resturantschadular.model.Meal
import com.example.resturantschadular.utl.getMeals
import com.example.resturantschadular.viewmodel.OrderViewModel

@Preview
@Composable
private fun prview() {
  //  MenuOrder()
}

@Composable
fun MenuOrder(viewModel: OrderViewModel) {

    val selectedMeals = remember { mutableStateListOf<Meal>() }
    var selectedAlgorithm by remember { mutableStateOf("Round Robin") }
    val scrollState = rememberScrollState()

    Column (
        modifier = Modifier
            .fillMaxSize().padding(20.dp)

    ){
        Text(text = "Order Your Meals", fontSize = 30.sp , fontWeight = FontWeight.Bold, color = Color.Black, modifier = Modifier.padding(horizontal = 20.dp))

        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(getMeals()) { meal ->
                    MealCard(meal, selectedMeals)
                }
            }
        }
        AlgorithmsSelection {algorithm ->
            selectedAlgorithm = algorithm
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {viewModel.scheduleOrders(selectedAlgorithm,selectedMeals)}
        ) {
            Text(text = "Schedule")
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
        colors = CardDefaults.cardColors(containerColor = Color.Gray)
    ){
        Row (
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(20.dp)
        ){
            Image(painter = painterResource(id= meal.icon), contentDescription = meal.name, modifier = Modifier.size(40.dp))
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

@Composable
fun AlgorithmsSelection(onAlgorithmSelected:(String)-> Unit) {
    val algorithmOptions = listOf("Round Robin", "FSFS", "SJN", "Priority Scheduling")

    Column {
        algorithmOptions.forEach{
            algorithm ->
            Button( modifier = Modifier.fillMaxWidth(), onClick = {onAlgorithmSelected(algorithm)}) { Text(text = algorithm) }
        }
    }
}