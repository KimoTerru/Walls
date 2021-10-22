package it.kimoterru.walls.util

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView

class DynamicHeightImageView : AppCompatImageView {
    private var whRatio = 0

    constructor(context: Context?, attrs: AttributeSet?) : super(
        context!!, attrs
    )

    constructor(context: Context?) : super(context!!)

    fun setRatio(ratio: Int) {
        whRatio = ratio
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        if (whRatio != 0) {
            val width = measuredWidth
            val height = (whRatio * width)
            setMeasuredDimension(width, height)
        }
    }
}