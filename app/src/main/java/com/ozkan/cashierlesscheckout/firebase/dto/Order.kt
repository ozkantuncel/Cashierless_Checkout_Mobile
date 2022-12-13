package com.ozkan.cashierlesscheckout.firebase.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Order(
    var date: String? = "",
    var isChecked: Boolean? = false
) : Parcelable
