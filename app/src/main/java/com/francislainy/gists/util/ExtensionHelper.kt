package com.francislainy.gists.util

import android.content.Context
import android.widget.Toast
import androidx.core.content.ContextCompat

fun Context.toast(message: String = "Clicked") {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun setColor(context: Context,colorId: Int): Int {

    return ContextCompat.getColor(context, colorId)
}
