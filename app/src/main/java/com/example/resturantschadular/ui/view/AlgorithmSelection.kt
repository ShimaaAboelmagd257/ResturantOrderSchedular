package com.example.resturantschadular.ui.view

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.*
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import androidx.navigation.NavController
import com.example.resturantschadular.utl.getAlgorithms
import com.example.resturantschadular.viewmodel.OrderViewModel

@Composable
fun AlgorithmsSelection(  viewModel: OrderViewModel , navController: NavController) {
    val context = LocalContext.current

    val selectedAlgorithm = remember { mutableStateOf("") }
    val algorithmOptions = getAlgorithms()



       Column (modifier = Modifier.padding(10.dp)){
           Spacer(modifier = Modifier.height(25.dp))

           Text(text = "Select an algorithm", fontSize = 22.sp , fontWeight = FontWeight.Bold, color = Color.Black, modifier = Modifier.padding( 16.dp))


           LazyVerticalGrid(
               columns = GridCells.Fixed(2),
               contentPadding = PaddingValues(8.dp),
               horizontalArrangement = Arrangement.spacedBy(12.dp),
               verticalArrangement = Arrangement.spacedBy(16.dp),
               modifier = Modifier
                   .fillMaxWidth()
                   .height(550.dp)
           ) {
               items(algorithmOptions) { algorithm ->
                val isSelected = selectedAlgorithm.value == algorithm.name

                Card(
                    modifier = Modifier
                        .width(210.dp).height(250.dp)
                        .alpha(if (selectedAlgorithm.value.isEmpty() || isSelected) 1f else 0.5f)
                        .clickable { selectedAlgorithm.value = algorithm.name
                             },
                    shape = RoundedCornerShape(20.dp),
                    elevation = CardDefaults.cardElevation(4.dp),
                    colors = CardDefaults.cardColors(
                        Color.White
                        //  containerColor = if(isSelected) Color.White else Color.LightGray
                    )
                ) {


                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Image(
                            painter = painterResource(id = algorithm.icon),
                            contentDescription = algorithm.name,
                            modifier = Modifier.fillMaxWidth().height(150.dp),
                            contentScale = ContentScale.FillBounds
                        )

                        Text(text = algorithm.name, fontSize = 16.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(vertical = 8.dp))
                        Button(
                            modifier = Modifier.fillMaxWidth().height(40.dp)
                                .padding(horizontal = 10.dp),
                            onClick = { selectedAlgorithm.value = algorithm.name
                                viewModel.setSelectedAlgorithm(selectedAlgorithm.value) },
                            colors = ButtonDefaults.buttonColors(containerColor = if (isSelected) Color.Green else Color.Black),
                        ) {
                            Text(text = if (isSelected) "Selected" else "Select")
                            Log.d("Res/OrderAlgorithmCard-", "isOrdered $isSelected")

                        }
                    }
                }

            }
        }
        Spacer(modifier = Modifier.height(25.dp))

        Button(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp),
            onClick = {
                when {
                    selectedAlgorithm.value.isNullOrEmpty() -> {
                        Toast.makeText(context, "Please Select an Algorithm!", Toast.LENGTH_SHORT)
                            .show()
                    }

                    else -> {
                        navController.navigate("scheduleJson")
                        Log.d(
                            "Res/OrderViewModel-selectedMeals",
                            " selectedAlgorithm = $selectedAlgorithm.value"
                        )
                    }
                }
            }, colors = ButtonDefaults.buttonColors(containerColor = Color.Black)

        ) {
            Text(text = "Schedule Your Order")
        }
    }
}
