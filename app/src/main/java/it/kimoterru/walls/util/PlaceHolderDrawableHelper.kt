package it.kimoterru.walls.util

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import java.util.*

object PlaceHolderDrawableHelper {
    private val placeholderValues = arrayOf(
        "#76A599", "#7D9A96", "#6F8888",
        "#82A1AB", "#8094A1", "#74838C",
        "#B6A688", "#9E9481", "#8D8F9F",
        "#A68C93", "#968388", "#7E808B"
    )
    private var drawableBackgroundList: MutableList<Drawable>? = null
    fun getBackgroundDrawable(position: Int): Drawable {
        if (drawableBackgroundList == null || drawableBackgroundList!!.size == 0) {
            drawableBackgroundList = ArrayList(placeholderValues.size)
            for (i in placeholderValues.indices) {
                val color = Color.parseColor(placeholderValues[i])
                (drawableBackgroundList as ArrayList<Drawable>).add(ColorDrawable(color))
            }
        }
        return drawableBackgroundList!![position % placeholderValues.size]
    }
}