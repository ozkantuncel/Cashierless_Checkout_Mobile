package com.ozkan.cashierlesscheckout.ui

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.navigation.compose.rememberNavController
import com.ozkan.cashierlesscheckout.ui.navigation.NavGraph
import com.ozkan.cashierlesscheckout.ui.navigation.Screen
 import com.ozkan.cashierlesscheckout.ui.theme.CashierlessCheckoutTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CashierlessCheckoutTheme {
                val navController = rememberNavController()
                NavGraph(navController = navController, startDestination =Screen.Home.route )
            }
        }
    }
}

