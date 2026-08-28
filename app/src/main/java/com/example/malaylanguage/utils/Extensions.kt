package com.example.malaylanguage.utils

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment

// Toast Extensions
fun Context.showToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}

fun Fragment.showToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    requireContext().showToast(message, duration)
}

// View Extensions
fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.GONE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.setVisible(visible: Boolean) {
    this.visibility = if (visible) View.VISIBLE else View.GONE
}

// String Extensions
fun String.isBanglaText(): Boolean {
    // Check if string contains Bengali characters
    return this.any { it.code in 2432..2559 }
}

fun String.removeExtraSpaces(): String {
    return this.trim().replace(Regex("\\s+"), " ")
}
