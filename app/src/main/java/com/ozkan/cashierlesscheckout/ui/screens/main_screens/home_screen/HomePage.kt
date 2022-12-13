package com.ozkan.cashierlesscheckout.ui.screens.main_screens

import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ozkan.cashierlesscheckout.R
import com.ozkan.cashierlesscheckout.data.dto.Product
import com.ozkan.cashierlesscheckout.firebase.dto.Order
import com.ozkan.cashierlesscheckout.ui.navigation.Screen
import com.ozkan.cashierlesscheckout.ui.screens.main_screens.common.BKAIconButton
import com.ozkan.cashierlesscheckout.ui.screens.main_screens.common.BTKDialogWithTextFiledAndButton
import com.ozkan.cashierlesscheckout.ui.screens.main_screens.common.BTKLoginButton
import com.ozkan.cashierlesscheckout.ui.screens.main_screens.home_screen.HomePageViewModel
import com.ozkan.cashierlesscheckout.ui.screens.main_screens.home_screen.card_payment.CardPayment
import com.ozkan.cashierlesscheckout.ui.theme.GreenApp
import com.ozkan.cashierlesscheckout.ui.theme.YellowApp
import com.ozkan.cashierlesscheckout.util.UiState
import com.ozkan.cashierlesscheckout.util.hideKeyboard
import com.ozkan.cashierlesscheckout.util.toast

@Composable
fun HomeScreen(
    navController: NavController,
    product: Product? = null,
    homePageViewModel: HomePageViewModel = hiltViewModel()
) {
    val homePageState = homePageViewModel.orderState.value
    val errorDialogState = remember { mutableStateOf(false) }
    val errorTitle = remember { mutableStateOf("") }
    val activity = LocalContext.current as Activity

    when (homePageState) {
        is UiState.Loading -> {

        }
        is UiState.Failure -> {

            homePageState.error?.let {
                activity.toast(it, Toast.LENGTH_LONG)
            }
        }
        is UiState.Success -> {
            homePageState.data.let {
                activity.toast(it, Toast.LENGTH_LONG)
            }
        }
        is UiState.Empty -> {}
    }

    HomePage(
        navController = navController,
        product = product,
        activity = activity,
        homePageViewModel = homePageViewModel
    )

}

@Composable
fun HomePage(
    navController: NavController,
    product: Product? = null,
    activity: Activity,
    homePageViewModel: HomePageViewModel
) {
    val balance = remember {
        mutableStateOf(100)
    }

    val balanceAdd = remember {
        mutableStateOf(false)
    }

    val balanceAddText = remember {
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
                        text = "${balance.value} TL",
                        color = Color.Black,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.SemiBold
                    )

                    BTKLoginButton(
                        modifier = Modifier.padding(top = 10.dp, bottom = 10.dp),
                        modifierButton = Modifier,
                        text = "Bakiye Yükle"
                    ) {
                        balanceAdd.value = true

                    }
                    Divider()
                }


                val total = remember {
                    mutableStateOf(0)
                }
                val dateP = remember {
                    mutableStateOf("")
                }



                if (product != null) {
                    LaunchedEffect(key1 = true) {
                        total.value = product.TotalPrice!!
                        dateP.value = product.Date!!
                    }
                    LazyColumn(
                        content = {
                            items(count = product.ProductNames.count(),
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
                }

                Text(
                    text = "${total.value}",
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


                    if (total.value != 0) {

                        if (balance.value >= total.value) {

                            val order = Order(
                                date = dateP.value,
                                isChecked = true
                            )
                            homePageViewModel.addOrder(order = order)
                        } else {
                            activity.toast("Bakiye yetersiz!!", length = Toast.LENGTH_LONG)
                        }
                    }
                }

                if (balanceAdd.value) {
                    BTKDialogWithTextFiledAndButton(
                        openTheDialog = balanceAdd,
                        content = {
                            Column(
                                modifier = Modifier
                                    .padding(horizontal = 20.dp, vertical = 10.dp)
                                    .background(
                                        color = Color.White,
                                        shape = RoundedCornerShape(15.dp)
                                    ),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            )
                            {

                                OutlinedTextField(
                                    textStyle = TextStyle(
                                        textAlign = TextAlign.Start,
                                        color = Color.Black
                                    ),
                                    value = balanceAddText.value,
                                    onValueChange = {
                                        balanceAddText.value = it
                                    },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(50.dp),
                                    shape = RoundedCornerShape(10.dp),
                                    singleLine = true,
                                    colors = TextFieldDefaults.outlinedTextFieldColors(
                                        focusedBorderColor = Color.LightGray,
                                        cursorColor = Color.Black
                                    ),
                                    placeholder = {
                                        Text(
                                            text = "Bakiye",
                                            fontSize = 15.sp
                                        )
                                    },
                                    keyboardOptions = KeyboardOptions(
                                        keyboardType = KeyboardType.Number
                                    ),
                                    keyboardActions = KeyboardActions(onDone = { activity.hideKeyboard() })
                                )

                                Button(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(
                                            all = 5.dp
                                        ),
                                    colors = ButtonDefaults.buttonColors(backgroundColor = GreenApp),
                                    onClick = {
                                        balanceAdd.value = false
                                        if (balanceAddText.value.isNotEmpty()) {
                                            balance.value += balanceAddText.value.toInt()
                                            activity.toast("Başarılı bir şekilde eklendi")
                                        } else {
                                            activity.toast("Lütfen bir değer giriniz")
                                        }
                                    }) {
                                    Text(
                                        text = "Ekle",
                                        style = TextStyle(textAlign = TextAlign.Center)
                                    )

                                }

                            }
                        },
                        title = "Bakiye Ekleme"
                    )
                }
            }
        },

        )
}

@Preview
@Composable
fun MyComponent() {

}