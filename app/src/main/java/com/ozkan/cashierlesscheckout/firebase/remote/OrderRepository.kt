package com.ozkan.cashierlesscheckout.firebase.remote

import com.ozkan.cashierlesscheckout.firebase.dto.Order
import com.ozkan.cashierlesscheckout.util.UiState

interface OrderRepository {
    fun updateOrder(
        order: Order,
        result: (UiState<String>) -> Unit
    )
}