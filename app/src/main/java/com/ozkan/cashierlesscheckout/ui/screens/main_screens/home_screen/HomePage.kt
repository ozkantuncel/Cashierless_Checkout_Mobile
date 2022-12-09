package com.ozkan.cashierlesscheckout.ui.screens.main_screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ozkan.cashierlesscheckout.R
import com.ozkan.cashierlesscheckout.data.dto.Product
import com.ozkan.cashierlesscheckout.ui.navigation.Screen
import com.ozkan.cashierlesscheckout.ui.screens.main_screens.common.BKAIconButton
import com.ozkan.cashierlesscheckout.ui.screens.main_screens.common.BTKLoginButton
import com.ozkan.cashierlesscheckout.ui.screens.main_screens.home_screen.card_payment.CardPayment
import com.ozkan.cashierlesscheckout.ui.theme.GreenApp
import com.ozkan.cashierlesscheckout.ui.theme.YellowApp

@Composable
fun HomePage(
    navController: NavController,
    product: Product? = null
) {
    val str = remember {
        mutableStateOf("")
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Kasiyersiz Kasa") },
                backgroundColor = YellowApp,
                actions = {
                    BKAIconButton(
                        modifier = Modifier,
                        iconModifier = Modifier.size(40.dp),
                        icon = R.drawable.qr_icon,
                        iconTint = Color.Black
                    ) {
                        // navController.popBackStack()//Bug
                        navController.navigate(Screen.QRScanner.route)
                    }
                }
            )
        },
        content = {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,

            ) {

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {

                    Text(
                        text = "Bakiye",
                        color = Color.Black,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.W300
                    )

                    Text(
                        text = "5000 TL",
                        color = Color.Black,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.SemiBold
                    )

                    BTKLoginButton(
                        modifier = Modifier.padding(top = 10.dp, bottom = 10.dp),
                        modifierButton = Modifier,
                        text = "Bakiye Yükle"
                    ) {
                        //TODO
                    }
                    Divider()
                }
                if (product != null) {
                    LazyColumn(
                        content = {
                            items(count =product.ProductNames.count(),
                            itemContent = {
                                val productName = product.ProductNames[it]
                                val producerName = product.ProducerNames[it]
                                val productTotalPrice = product.ProductTotalPrice[it]
                                val productTax = product.ProductTax[it]
                                CardPayment(
                                    productName = productName,
                                    producerName = producerName,
                                    productTotalP = productTotalPrice,
                                    productTax = productTax
                                )
                            })
                        }
                    )
                    Text(
                        text = "${product.TotalPrice}",
                        color = Color.Black,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.SemiBold
                    )

                    BTKLoginButton(
                        modifier = Modifier.padding(top = 10.dp, bottom = 10.dp),
                        modifierButton = Modifier.size(width = 150.dp, height = 50.dp),
                        text = "Ödeme Yap",
                        fontSize = 15.sp
                    ) {
                        //TODO
                    }
                }


               // CardPayment()
            }
        },

        )
}