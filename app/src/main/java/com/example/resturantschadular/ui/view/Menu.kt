package com.example.resturantschadular.ui.view

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.pager.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import androidx.navigation.NavController
import com.example.resturantschadular.model.Meal
import com.example.resturantschadular.utl.getMeals
import com.example.resturantschadular.viewmodel.OrderViewModel
import kotlin.math.absoluteValue

@Preview
@Composable
private fun prview() {
    //MealCard(m)
}

@Composable
fun MenuOrder(viewModel: OrderViewModel, navController: NavController) {

    val mealMenu = getMeals()
    val pagerState = rememberPagerState(pageCount = { mealMenu.size })

    val selectedMeals = remember { mutableStateListOf<Meal>() }
    var selectedAlgorithm by remember { mutableStateOf("Round Robin") }
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)

    ){

        Text(text = "Order Your Meals", fontSize = 30.sp , fontWeight = FontWeight.Bold, color = Color.Black, modifier = Modifier.padding(  20.dp))

            HorizontalPager(state = pagerState, contentPadding = PaddingValues(horizontal = 20.dp)
            ) {page ->
                val selectedMeal = mealMenu[page]
                val pageOffset = (
                        (pagerState.currentPage - page) + pagerState
                            .currentPageOffsetFraction
                        ).absoluteValue
                val alpha = lerp(0.5f, 1f, 1f - pageOffset.coerceIn(0f, 1f))
                MealCard(
                    meal = selectedMeal ,
                    showCheckBox = true,
                    isSelected = selectedMeal in selectedMeals,
                    onSelectionChange = {
                            selected ->
                        if (selected) selectedMeals.add(selectedMeal) else selectedMeals.remove(selectedMeal)

                    },
                    alpha = alpha

                )
            }


       /* AlgorithmsSelection {algorithm ->
            selectedAlgorithm = algorithm
        }*/

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                viewModel.scheduleOrders(selectedAlgorithm,selectedMeals)
                navController.navigate("schedule")
            }

        ) {
            Text(text = "Schedule Your Order")
        }
    }
}

@Composable
fun MealCard(meal:Meal,
             showCheckBox: Boolean = false,
             isSelected: Boolean = false,
             onSelectionChange:((Boolean)->Unit)? =null,
             timeServed:String?= null,
             alpha: Float
) {

    Card (modifier = Modifier
        .size(450.dp).padding(10.dp)
        .graphicsLayer {
            this.alpha = alpha
        }


        .clickable {
            if (showCheckBox) onSelectionChange?.invoke(!isSelected)
        },
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ){

            Image(painter = painterResource(id= meal.icon), contentDescription = meal.name, modifier = Modifier.size(300.dp), contentScale = ContentScale.Crop)
            Spacer(modifier = Modifier.height(10.dp))
        Column  (
              modifier = Modifier.padding(start = 5.dp),
            // modifier = Modifier.padding(20.dp)
        ) {
            Text(text = meal.name, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
            Text(
                text = "Ready for " + meal.prepTime + "Min",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )

            Spacer(modifier = Modifier.height(15.dp))
            Text(text = meal.caption, fontSize = 16.sp, fontWeight = FontWeight.Normal)

            /* if (showCheckBox){
                Checkbox(
                    checked = isSelected,
                    onCheckedChange =  onSelectionChange

                )
            }*/
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