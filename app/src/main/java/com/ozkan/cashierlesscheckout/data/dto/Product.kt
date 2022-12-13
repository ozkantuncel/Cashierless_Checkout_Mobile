package com.ozkan.cashierlesscheckout.data.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    @SerializedName("Date")
    var Date: String? = "",
    @SerializedName("TotalPrice")
    var TotalPrice: Int? = 0,
    @SerializedName("ProductNames")
    var ProductNames: List<String> = listOf(),
    @SerializedName("ProducerNames")
    var ProducerNames: List<String> = listOf(),
    @SerializedName("ProductTotalPrice")
    var ProductTotalPrice: List<Int> = listOf(),
    @SerializedName("ProductTax")
    var ProductTax: List<Int> = listOf()
) : Parcelable