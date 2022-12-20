package com.ozkan.cashierlesscheckout.firebase.dto

import android.os.Parcelable
import com.google.firebase.database.PropertyName
import kotlinx.parcelize.Parcelize


@Parcelize
data class Order(
    @PropertyName("Date")
    var Date: String? = "",
    @PropertyName("IsChecked")
    var IsChecked: Boolean? = false
) : Parcelable
