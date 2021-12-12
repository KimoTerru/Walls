package it.kimoterru.walls.adapter.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import it.kimoterru.walls.R
import it.kimoterru.walls.adapter.WallpaperClickListener
import it.kimoterru.walls.data.models.photo.PhotoItem

class LatestAdapter(
    val data: List<PhotoItem>,
    private val listener: WallpaperClickListener.WallpaperClick,
    @LayoutRes val viewId: Int
) : RecyclerView.Adapter<LatestAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.card_wallpaper)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(viewId, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        Glide.with(holder.image).load(item.urls.thumb).into(holder.image)
        holder.image.setOnClickListener {
            listener.onWallpaperClick(item.id, item.user.profileImage.large, item.id_photo)
        }
    }

    override fun getItemCount() = data.size
}