package com.example.resturantschadular.ui.view

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import androidx.navigation.NavController
import com.example.resturantschadular.viewmodel.ClientViewModel
import com.example.resturantschadular.viewmodel.OrderViewModel
import kotlin.math.absoluteValue


@Composable
fun MenuOrder(viewModel: OrderViewModel,clientViewModel: ClientViewModel, navController: NavController) {
    val mealMenu = viewModel.mealMenu
  //  val mealMenu = getMeals()
    val pagerState = rememberPagerState(pageCount = { mealMenu.size })
  //  val selectedMeals = remember { mutableStateListOf<Meal>() }
    val selectedAlgorithm = remember { mutableStateOf("") }
    val scrollState = rememberScrollState()
    val selectedMealNames = remember { mutableStateListOf<String>() }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
            .verticalScroll(scrollState)
    ){
        Spacer(modifier = Modifier.height(20.dp))

        Text(text = "Order Your Meals", fontSize = 30.sp , fontWeight = FontWeight.Bold, color = Color.Black, modifier = Modifier.padding(  10.dp))

            HorizontalPager(state = pagerState, contentPadding = PaddingValues(horizontal = 20.dp)
            ) {page ->
                val meal = mealMenu[page]
                val pageOffset = (
                        (pagerState.currentPage - page) + pagerState
                            .currentPageOffsetFraction
                        ).absoluteValue
                val alpha = lerp(0.5f, 1f, 1f - pageOffset.coerceIn(0f, 1f))
                MealCard(
                    meal = meal ,
                    showOrderButton = true,
                    isOrdered = meal.name in selectedMealNames,
                    onOrderClick = {
                        if (meal.name in selectedMealNames) {
                            selectedMealNames.remove(meal.name)
                        } else {
                        selectedMealNames.add(meal.name)
                    }
                    },
                    alpha = alpha

                )
            }

        Text(text = "Select an algorithm", fontSize = 30.sp , fontWeight = FontWeight.Bold, color = Color.Black, modifier = Modifier.padding( 10.dp))

        AlgorithmsSelection ( selectedAlgorithm = selectedAlgorithm.value,
            onAlgorithmSelected = {algorithm -> selectedAlgorithm.value = algorithm }
        )

        Spacer(modifier = Modifier.height(10.dp))

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                val selectedMeals = mealMenu.filter { it.name in selectedMealNames }
                viewModel.scheduleOrders(selectedAlgorithm.value, selectedMeals,clientViewModel)
                navController.navigate("schedule")
                Log.d("Res/OrderViewModel-selectedMeals"," selectedMeals = $selectedMeals")
            }, colors = ButtonDefaults.buttonColors(containerColor =Color.Black)

            ) {
            Text(text = "Schedule Your Order")
        }
    }
}


