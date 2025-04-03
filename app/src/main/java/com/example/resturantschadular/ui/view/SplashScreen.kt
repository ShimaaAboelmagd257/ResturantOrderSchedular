@file:Suppress("IMPLICIT_CAST_TO_ANY")

package com.example.resturantschadular.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import kotlinx.coroutines.delay

@Composable
fun GetLottieAnimation(navController: NavController,  animation: String = "", caption:String? = null) {

    LaunchedEffect(Unit) {
        delay(5000) // 3 seconds delay
        val destination = when (animation) {
            "splashJson" -> "menu"
            "scheduleJson" -> "schedule"
            else -> "menu" // Default destination
        }

        navController.navigate(destination)
    }

    val animationFile = when(animation){
        "splashJson" -> "chef_burning.json"
        "scheduleJson" -> "chef_dancing.json"
        else -> "chef_dancing.json"
    }
    val composition by rememberLottieComposition(
        LottieCompositionSpec.Asset(animationFile)
    )
    Box(
        modifier = Modifier
            .fillMaxSize(),

        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            LottieAnimation(
                composition = composition,
                modifier = Modifier.size(250.dp).fillMaxWidth() // Adjust size as needed
            )

            Spacer(modifier = Modifier.height(20.dp))

           caption?.let {
            Text(
                text = caption,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }

      }
    }

}
