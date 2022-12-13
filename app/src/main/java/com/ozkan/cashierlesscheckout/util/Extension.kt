package com.ozkan.cashierlesscheckout.util

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast


fun Activity.hideKeyboard() {
    hideKeyboard(currentFocus ?: View(this))
}


fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}


fun Context.toast(message: String?, length: Int = Toast.LENGTH_SHORT) {
    message?.let {
        Toast.makeText(this, message, length).show()
    }
}


fun Context.toast(message: Int?, length: Int = Toast.LENGTH_SHORT) {
    message?.let {
        Toast.makeText(this, this.getText(message), length).show()
    }
}