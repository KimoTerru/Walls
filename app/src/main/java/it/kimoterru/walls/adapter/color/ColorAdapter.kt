package it.kimoterru.walls.adapter.color

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import it.kimoterru.walls.R
import it.kimoterru.walls.adapter.WallpaperClickListener
import it.kimoterru.walls.models.search.SearchItem
import it.kimoterru.walls.util.PlaceHolderDrawableHelper

class ColorAdapter(
    private val data: SearchItem,
    private val listener: WallpaperClickListener.WallpaperClick,
    @LayoutRes val viewId: Int
) : RecyclerView.Adapter<ColorAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.card_image_display)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(viewId, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data.results[position]

        holder.image.measure(item.width, item.height)
        Glide.with(holder.image).load(item.urls.small)
            .placeholder(PlaceHolderDrawableHelper.getBackgroundDrawable(position, item.color))
            .into(holder.image)

        holder.image.setOnClickListener {
            listener.onWallpaperClick(
                item.id,
                item.urls.full,
                item.links.download,
                item.createdAt,
                item.updatedAt,
                item.user.username,
                item.user.name
            )
        }
    }

    override fun getItemCount() = data.results.size
}