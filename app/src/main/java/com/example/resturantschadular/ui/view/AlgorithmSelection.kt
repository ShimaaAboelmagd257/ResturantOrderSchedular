package com.example.resturantschadular.ui.view

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import com.example.resturantschadular.utl.getAlgorithms

@Composable
fun AlgorithmsSelection(onAlgorithmSelected:(String)-> Unit, selectedAlgorithm:String ) {
    val algorithmOptions = getAlgorithms()
    val horizontalScroll = rememberScrollState()

    Row (
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(horizontalScroll),
        horizontalArrangement = Arrangement.spacedBy(15.dp)
    ){
        algorithmOptions.forEach { algorithm ->
            val isSelected = selectedAlgorithm == algorithm.name

            Card(
                modifier = Modifier
                    .size(190.dp)
                    .alpha(if (selectedAlgorithm.isEmpty() || isSelected) 1f else 0.5f)
                    .clickable { onAlgorithmSelected(algorithm.name) },
                shape = RoundedCornerShape(20.dp),
                elevation = CardDefaults.cardElevation(4.dp),
                colors = CardDefaults.cardColors(
                    Color.White
                    //  containerColor = if(isSelected) Color.White else Color.LightGray
                )
            ) {

                Column (
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(5.dp)
                ){
                    Image(painter = painterResource(id= algorithm.icon), contentDescription = algorithm.name, modifier = Modifier.fillMaxWidth().height(100.dp), contentScale = ContentScale.FillBounds)
                    Text(text = algorithm.name, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    Button(
                        modifier = Modifier.fillMaxWidth().size(40.dp).padding(horizontal = 10.dp),
                        onClick = { onAlgorithmSelected(algorithm.name) },
                        colors = ButtonDefaults.buttonColors(containerColor = if(isSelected) Color.Green else Color.Black),
                    ) {
                        Text(text = if(isSelected) "Selected" else "Select")
                        Log.d("Res/OrderAlgorithmCard-", "isOrdered $isSelected")

                    }
                }
            }

        }
    }
}