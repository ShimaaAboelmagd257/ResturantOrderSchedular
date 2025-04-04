package com.example.resturantschadular.utl

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.resturantschadular.ui.view.AlgorithmsSelection
import com.example.resturantschadular.ui.view.GetLottieAnimation
import com.example.resturantschadular.ui.view.MenuOrder
import com.example.resturantschadular.ui.view.ScheduledOrders
import com.example.resturantschadular.viewmodel.OrderViewModel

@Composable
fun AppNavigation() {
    val navController= rememberNavController()
    val orderViewModel: OrderViewModel = OrderViewModel()


    NavHost(navController= navController, startDestination = "splashJson"){
        composable("splashJson") { GetLottieAnimation(navController = navController,"splashJson", "Please help me") }
        composable("menu") { MenuOrder(viewModel = orderViewModel,navController) }
        composable("selectAlgorithm") { AlgorithmsSelection(viewModel = orderViewModel,navController) }
        composable("scheduleJson") { GetLottieAnimation(navController = navController,"scheduleJson", "Scheduling the orders") }
        composable("schedule") { ScheduledOrders(orderViewModel,navController)  }
    }
}

