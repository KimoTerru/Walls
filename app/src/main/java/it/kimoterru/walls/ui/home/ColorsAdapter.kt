package it.kimoterru.walls.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import it.kimoterru.walls.R
import it.kimoterru.walls.data.local.model.color.Colors
import it.kimoterru.walls.util.WallpaperClickListener

class ColorAdapter(
    private val colors: List<Colors>,
    private val listener: WallpaperClickListener.HomeFragment,
) : RecyclerView.Adapter<ColorAdapter.ViewHolder>() {

    class ViewHolder internal constructor(view: View) : RecyclerView.ViewHolder(view) {
        val colorCardView: ImageView = view.findViewById<View>(R.id.color_card_view) as ImageView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.card_color, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = colors[position]
        holder.colorCardView.setImageResource(item.color)
        holder.colorCardView.setOnClickListener {
            listener.onColorClick(item.name)
        }
    }

    override fun getItemCount() = colors.size
}