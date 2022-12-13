package com.ozkan.cashierlesscheckout.ui.screens.main_screens.home_screen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.ozkan.cashierlesscheckout.firebase.dto.Order
import com.ozkan.cashierlesscheckout.firebase.remote.OrderRepository
import com.ozkan.cashierlesscheckout.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomePageViewModel @Inject constructor(
    private val repository: OrderRepository
) : ViewModel() {
    private val _order: MutableState<UiState<String>> = mutableStateOf(UiState.Empty)
    val orderState: State<UiState<String>>
        get() = _order

    fun addOrder(
        order: Order,
    ) {
        _order.value = UiState.Loading
        repository.updateOrder(
            order = order,
        ) {
            _order.value = it
        }
    }
}