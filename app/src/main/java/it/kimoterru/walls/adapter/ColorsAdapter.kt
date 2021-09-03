package it.kimoterru.walls.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import it.kimoterru.walls.R
import it.kimoterru.walls.models.color.Colors

class ColorAdapter(
    private val listener: WallpaperClickListener,
    @LayoutRes val viewId: Int,
) : RecyclerView.Adapter<ColorAdapter.ViewHolder>() {

    private val colors: List<Colors> = listOf(
        Colors("black", R.color.wp_black),
        Colors("white", R.color.alice_blue),
        Colors("yellow", R.color.wp_yellow),
        Colors("orange", R.color.wp_orange),
        Colors("red", R.color.wp_red),
        Colors("purple", R.color.wp_purple),
        Colors("magenta", R.color.wp_magenta),
        Colors("green", R.color.wp_green),
        Colors("teal", R.color.wp_teal),
        Colors("blue", R.color.wp_blue)
    )

    class ViewHolder internal constructor(view: View) : RecyclerView.ViewHolder(view) {
        val colorCard: ImageView = view.findViewById<View>(R.id.card_color) as ImageView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(viewId, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = colors[position]
        holder.colorCard.setImageResource(item.color)
        holder.colorCard.setOnClickListener {
            listener.onColorClick(item.name)
        }
    }

    override fun getItemCount() = colors.size
}