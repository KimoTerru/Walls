package it.kimoterru.walls.util

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable

object PlaceHolderDrawableHelper {
    private val placeholderValues: MutableList<String> = ArrayList()
    private var drawableBackgroundList: MutableList<Drawable>? = null

    fun getBackgroundDrawable(position: Int, colors: String): Drawable {
        if (drawableBackgroundList.isNullOrEmpty()) {
            placeholderValues.add(colors)
            drawableBackgroundList = ArrayList(placeholderValues.size)
            for (i in placeholderValues.indices) {
                val color = Color.parseColor(placeholderValues[i])
                (drawableBackgroundList as ArrayList<Drawable>).add(ColorDrawable(color))
            }
        } else {
            placeholderValues.clear()
            placeholderValues.add(colors)
            drawableBackgroundList = ArrayList(placeholderValues.size)
            for (i in placeholderValues.indices) {
                val color = Color.parseColor(placeholderValues[i])
                (drawableBackgroundList as ArrayList<Drawable>).add(ColorDrawable(color))
            }
        }
        return drawableBackgroundList!![position % placeholderValues.size]
    }
}