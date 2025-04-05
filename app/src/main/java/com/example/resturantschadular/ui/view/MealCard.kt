package com.example.resturantschadular.ui.view

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.resturantschadular.model.Meal
import com.example.resturantschadular.utl.formatTimestamp

@Composable
fun MealCard(meal: Meal,
             showOrderButton: Boolean = false,
             isOrdered: Boolean = false,
             onOrderClick:()->Unit,
             alpha: Float
) {

    Card (modifier = Modifier
        .size(500.dp)
        .padding(10.dp)
        .graphicsLayer {
            this.alpha = alpha
        },
        shape = RoundedCornerShape(35.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ){
        Image(painter = painterResource(id= meal.icon), contentDescription = meal.name, modifier = Modifier.size(300.dp), contentScale = ContentScale.Crop)

        Column  (
           // modifier = Modifier.fillMaxSize(),
             modifier = Modifier.padding(5.dp)
        ) {
            Spacer(modifier = Modifier.height(10.dp))

            Text(text = meal.name, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
            Text(
                text = "Ready after " + meal.prepTime + "Min",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )


            if (showOrderButton){
                Spacer(modifier = Modifier.height(15.dp))
                Text(text = meal.caption, fontSize = 16.sp, fontWeight = FontWeight.Normal)

                Button(
                    onClick = onOrderClick,
                    colors = ButtonDefaults.buttonColors(containerColor = if(isOrdered) Color.Green else Color.Black),
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth()
                ) {
                    Text(text = if(isOrdered) "Ordered" else "Order Now")
                    Log.d("Res/OrderMenuMealCrd-","isOrdered $isOrdered")

                }
            }else{
                Spacer(modifier = Modifier.height(15.dp))
               /* Text(
                    text = "Arrival: ${formatTimestamp(meal.arrivalTime)}",
                    style = MaterialTheme.typography.bodyMedium
                )*/
                Text(
                    text = "Prep Time: ${meal.prepTime} min",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "Start Time: ${formatTimestamp(meal.startTime)}",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "Served Time: ${formatTimestamp(meal.servedTime)}",
                    style = MaterialTheme.typography.bodyMedium
                )
            }


        }
    }
}

