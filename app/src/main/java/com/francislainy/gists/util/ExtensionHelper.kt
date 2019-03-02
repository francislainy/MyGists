package com.francislainy.gists.util

import android.content.Context
import android.widget.Toast

fun Context.toast(message: String = "Clicked") {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}