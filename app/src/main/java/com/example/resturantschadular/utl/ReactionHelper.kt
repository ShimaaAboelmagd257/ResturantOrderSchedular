package com.example.resturantschadular.utl

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.*

// ToDo
@Composable
fun GetLottieReactionAnimation(reaction: String) {

    val context = LocalContext.current

    val animationFile = when (reaction) {
        "happy" -> "happy.json"
        "sad" -> "sad.json"
        "angry" -> "angry.json"
        else -> ""
    }
      val composition by rememberLottieComposition(
        LottieCompositionSpec.Asset(animationFile)
    )
    LottieAnimation(
        composition = composition,
        modifier = Modifier.size(150.dp)
    )
}
