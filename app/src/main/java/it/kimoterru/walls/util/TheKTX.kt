package it.kimoterru.walls.util

import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import java.util.*

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.isVisible(show: Boolean) {
    if (show) {
        visible()
    } else {
        gone()
    }
}

fun Fragment.showToast(message: String?) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

fun isNight(): Boolean {
    val currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
    return (currentHour <= 7 || currentHour >= 18)
} //Soon check day or night