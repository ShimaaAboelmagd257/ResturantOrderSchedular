package com.example.resturantschadular.ui.view

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.alpha
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
import com.example.resturantschadular.utl.getAlgorithms
import com.example.resturantschadular.utl.getLottieReactionAnimation
import com.example.resturantschadular.utl.getMeals
import com.example.resturantschadular.viewmodel.ClientViewModel
import com.example.resturantschadular.viewmodel.OrderViewModel
import kotlin.math.absoluteValue

@Preview
@Composable
private fun preview() {
    //MealCard(m)
}

@Composable
fun MenuOrder(viewModel: OrderViewModel,clientViewModel: ClientViewModel, navController: NavController) {

    val mealMenu = getMeals()
    val pagerState = rememberPagerState(pageCount = { mealMenu.size })
    val selectedMeals = remember { mutableStateListOf<Meal>() }
    var selectedAlgorithm = viewModel.selectedAlgorithm
    val scrollState = rememberScrollState()

    
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
            .verticalScroll(scrollState)

    ){

        Text(text = "Order Your Meals", fontSize = 30.sp , fontWeight = FontWeight.Bold, color = Color.Black, modifier = Modifier.padding(  20.dp))

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
                    isOrdered = meal in selectedMeals,
                    onOrderClick = {

                        if (meal !in selectedMeals) {
                            selectedMeals.add(meal)
                            clientViewModel.addClientOrder(meal)
                        } else selectedMeals.remove(meal)

                    },
                    alpha = alpha

                )
            }

        AlgorithmsSelection (selectedAlgorithm = selectedAlgorithm,
            onAlgorithmSelected = {algorithm -> selectedAlgorithm = algorithm }
        )



        Spacer(modifier = Modifier.height(20.dp))

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                selectedMeals.forEach{ clientViewModel.addClientOrder(it)}
                viewModel.scheduleOrders(selectedAlgorithm, selectedMeals)

                navController.navigate("schedule")
            }

        ) {
            Text(text = "Schedule Your Order")
        }
    }
}

@Composable
fun MealCard(meal:Meal,
             showOrderButton: Boolean = false,
             isOrdered: Boolean = false,
             onOrderClick:()->Unit,
             showReaction:Boolean = false,
             reaction: String?=null,
             alpha: Float
) {

    Card (modifier = Modifier
        .size(500.dp)
        .padding(10.dp)
        .graphicsLayer {
            this.alpha = alpha
        },
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ){
        Column  (
            modifier = Modifier.padding( 5.dp),
            // modifier = Modifier.padding(20.dp)
        ) {
            Image(painter = painterResource(id= meal.icon), contentDescription = meal.name, modifier = Modifier.size(300.dp), contentScale = ContentScale.Crop)
            Spacer(modifier = Modifier.height(10.dp))

            Text(text = meal.name, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
            Text(
                text = "Ready for " + meal.prepTime + "Min",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )

            Spacer(modifier = Modifier.height(15.dp))
            Text(text = meal.caption, fontSize = 16.sp, fontWeight = FontWeight.Normal)

             if (showOrderButton){
               Button(
                   onClick = onOrderClick,
                   colors = ButtonDefaults.buttonColors(containerColor = if(isOrdered) Color.Green else Color.Black),
                   modifier = Modifier
                       .padding(10.dp)
                       .fillMaxWidth()
               ) {
                   Text(text = if(isOrdered) "Ordered" else "Order Now")
               }
            }

            if (showReaction && reaction != null){
                getLottieReactionAnimation(reaction)
            }
        }
    }
}

@Composable
fun AlgorithmsSelection(onAlgorithmSelected:(String)-> Unit, selectedAlgorithm:String) {
    val algorithmOptions = getAlgorithms()
    val horizontalScroll = rememberScrollState()

    Row (
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(horizontalScroll),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ){
      algorithmOptions.forEach { algorithm ->
          val isSelected = selectedAlgorithm == algorithm.name
          Card(
              modifier = Modifier
                  .size(120.dp)
                 // .alpha(if (selectedAlgorithm.isEmpty() || isSelected) 1f else 0.5f)
              ,
                  //.clickable { if (!isSelected) onAlgorithmSelected(algorithm.name) },
              shape = RoundedCornerShape(15.dp),
              elevation = CardDefaults.cardElevation(4.dp),
              colors = CardDefaults.cardColors(
                  //containerColor = if(!isSelected) Color.White else Color.LightGray
              )
          ) {

              Column (
                  modifier = Modifier.fillMaxSize(),
                  horizontalAlignment = Alignment.CenterHorizontally
              ){
                  Image(painter = painterResource(id= algorithm.icon), contentDescription = algorithm.name, modifier = Modifier.size(15.dp), contentScale = ContentScale.Crop)
                  Text(text = algorithm.name, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
                  Button(
                      onClick = { /*if(isSelected)*/ onAlgorithmSelected(algorithm.name) },
                      colors = ButtonDefaults.buttonColors(containerColor = if(isSelected) Color.Green else Color.Black),

                      // enabled =  !isSelected
                  ) {
                      Text(text = if(isSelected) "Selected" else "Select")
                  }
              }
          }

      }
    }
}