package it.kimoterru.walls.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import it.kimoterru.walls.R
import it.kimoterru.walls.data.remote.models.color.Colors
import it.kimoterru.walls.util.WallpaperClickListener

class ColorAdapter(
    private val colors: List<Colors>,
    private val listener: WallpaperClickListener.HomeFragment,
    @LayoutRes val viewId: Int,
) : RecyclerView.Adapter<ColorAdapter.ViewHolder>() {

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