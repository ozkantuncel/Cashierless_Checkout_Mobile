package com.ozkan.cashierlesscheckout.di

import android.content.Context
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class CashierlessCheckoutApp:CoreLibApplication(){
    init {
        instance = this
    }

    companion object {
        lateinit var instance: CashierlessCheckoutApp

        fun applicationContext(): Context {
            return instance.applicationContext
        }
    }
}