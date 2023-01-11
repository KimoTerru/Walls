package it.kimoterru.walls.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import it.kimoterru.walls.R
import it.kimoterru.walls.domain.models.photo.Photo
import it.kimoterru.walls.util.Constants
import it.kimoterru.walls.util.WallpaperClickListener

class LatestAdapter(
    val data: List<Photo>,
    private val listener: WallpaperClickListener.WallpaperClick
) : RecyclerView.Adapter<LatestAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val wallpaperCardView: ImageView = itemView.findViewById(R.id.wallpaper_card_view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.card_image, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        Glide.with(holder.wallpaperCardView).load(item.urls?.thumb)
            .transition(DrawableTransitionOptions.withCrossFade(Constants.CROSS_FADE_DURATION))
            .into(holder.wallpaperCardView)
            .clearOnDetach()
        holder.wallpaperCardView.setOnClickListener {
            listener.onWallpaperClick(
                item.id!!,
                item.id_photo_is_local,
                item.user?.profileImage?.large!!
            )
        }
    }

    override fun getItemCount() = data.size
}