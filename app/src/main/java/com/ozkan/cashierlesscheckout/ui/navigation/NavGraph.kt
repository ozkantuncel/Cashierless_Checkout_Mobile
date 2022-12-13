package com.ozkan.cashierlesscheckout.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ozkan.cashierlesscheckout.data.dto.Product
import com.ozkan.cashierlesscheckout.ui.screens.main_screens.HomeScreen
import com.ozkan.cashierlesscheckout.ui.screens.main_screens.qr_screen.QRPage

@Composable
fun NavGraph(
    navController: NavHostController,
    startDestination: String
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(route = Screen.Home.route) {
            val obj =
                navController.previousBackStackEntry?.savedStateHandle?.get<Product>("productItem")
            HomeScreen(navController = navController, product = obj)
        }
        composable(route = Screen.QRScanner.route) {
            QRPage(navController)
        }
    }
}