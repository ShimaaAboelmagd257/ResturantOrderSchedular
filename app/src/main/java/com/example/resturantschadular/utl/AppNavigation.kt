package com.example.resturantschadular.utl

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.resturantschadular.ui.view.MenuOrder
import com.example.resturantschadular.ui.view.ScheduledOrders
import com.example.resturantschadular.viewmodel.ClientViewModel
import com.example.resturantschadular.viewmodel.OrderViewModel

@Composable
fun AppNavigation() {
    val navController= rememberNavController()
    val orderViewModel: OrderViewModel = OrderViewModel()
    val clientViewModel: ClientViewModel = ClientViewModel()

    NavHost(navController= navController, startDestination = "menu"){
        composable("menu") { MenuOrder(viewModel = orderViewModel,clientViewModel,navController) }
        composable("schedule") { ScheduledOrders(orderViewModel,clientViewModel,navController)  }
    }
}

