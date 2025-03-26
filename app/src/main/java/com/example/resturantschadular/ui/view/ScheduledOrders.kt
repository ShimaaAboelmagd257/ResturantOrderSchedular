package com.example.resturantschadular.ui.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import androidx.navigation.NavController
import com.example.resturantschadular.model.Client
import com.example.resturantschadular.utl.GetLottieReactionAnimation
import com.example.resturantschadular.viewmodel.ClientViewModel
import com.example.resturantschadular.viewmodel.OrderViewModel
import kotlin.math.absoluteValue

@Composable
fun ScheduledOrders(
    orderViewModel: OrderViewModel,
    clientViewModel: ClientViewModel,
    navController: NavController
) {

    val selectedAlgorithm = orderViewModel.selectedAlgorithm
    val scheduledOrders by orderViewModel.scheduledOrders.collectAsState()
    val pagerState = rememberPagerState(pageCount = { scheduledOrders.size })

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(40.dp)
    ) {
        Text(
            text = "Scheduled Orders selected by $selectedAlgorithm ",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )

        HorizontalPager(state = pagerState, contentPadding = PaddingValues(horizontal = 20.dp)
        ) {page ->
            val scheduleMeal = scheduledOrders[page]
            val pageOffset = (
                    (pagerState.currentPage - page) + pagerState
                        .currentPageOffsetFraction
                    ).absoluteValue
            val alpha = lerp(0.5f, 1f, 1f - pageOffset.coerceIn(0f, 1f))

                MealCard(
                    meal = scheduleMeal ,
                    isOrdered = true,
                    onOrderClick = {},
                    alpha = alpha

                )

        }

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { navController.navigate("menu") }
        ) {
            Text(text = "Back to menu")
        }

    }

    
}

@Composable
fun ClientCard(client: Client) {
   Card(modifier = Modifier.padding(10.dp)) {
       Column (horizontalAlignment = Alignment.CenterHorizontally){
           Text(text = client.meal.name, fontSize = 20.sp)
           GetLottieReactionAnimation(client.reaction)
       }
   }


}


