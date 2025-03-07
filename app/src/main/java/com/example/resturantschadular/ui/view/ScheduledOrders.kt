package com.example.resturantschadular.ui.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.resturantschadular.viewmodel.OrderViewModel

@Composable
fun ScheduledOrders(viewModel: OrderViewModel, navController: NavController) {

    val scheduledOrders = viewModel.scheduledOrders
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Text(
            text = "Scheduled Orders",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )

        LazyColumn (
            modifier = Modifier.weight(1f)
        ){
           items(scheduledOrders){
               meal -> MealCard(meal = meal)
           }
        }
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { navController.navigate("menu") }
        ) {
            Text(text = "Back to menu")
        }

    }
    
}


