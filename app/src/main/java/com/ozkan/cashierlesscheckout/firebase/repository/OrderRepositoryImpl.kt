package com.ozkan.cashierlesscheckout.firebase.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import com.ozkan.cashierlesscheckout.firebase.dto.Order
import com.ozkan.cashierlesscheckout.firebase.remote.OrderRepository
import com.ozkan.cashierlesscheckout.util.Constants
import com.ozkan.cashierlesscheckout.util.UiState

class OrderRepositoryImpl(
    private val database: FirebaseFirestore,
    private val gson: Gson
) : OrderRepository {
    override fun updateOrder(
        order: Order,
        result: (UiState<String>) -> Unit
    ) {

        val doc =
            database.collection(Constants.FirebaseFireStoreConstants.ORDER).document(order.date!!)
        doc.set(order)
            .addOnSuccessListener {
                result.invoke(
                    UiState.Success("Başarılı bir şeklide eklendi")
                )
            }.addOnFailureListener {
                result.invoke(
                    UiState.Failure(
                        it.localizedMessage
                    )
                )
            }
    }

}