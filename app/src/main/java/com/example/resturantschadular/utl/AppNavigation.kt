package com.example.resturantschadular.utl

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.resturantschadular.ui.view.MenuOrder
import com.example.resturantschadular.ui.view.ScheduledOrders
import com.example.resturantschadular.viewmodel.OrderViewModel

@Composable
fun AppNavigation() {
    val navController= rememberNavController()
    val viewModel: OrderViewModel = OrderViewModel()

    NavHost(navController= navController, startDestination = "menu"){
        composable("menu") { MenuOrder(viewModel = viewModel,navController) }
        composable("schedule") { ScheduledOrders(viewModel,navController)  }
    }
}

