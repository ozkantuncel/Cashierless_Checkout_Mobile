package com.ozkan.cashierlesscheckout.ui.navigation

sealed class Screen(val route:String){
    object Home:Screen(route = "HomePage")
    object QRScanner: Screen(route = "QRScanner")
}
