package it.kimoterru.walls.adapter.searsh

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

class SearchAdapter(
    private val data: SearchItem,
    private val listener: WallpaperClickListener.WallpaperClick,
    @LayoutRes val viewId: Int
) : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageCategory: ImageView = view.findViewById(R.id.card_image_search)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(viewId, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data.results[position]
        Glide.with(holder.imageCategory).load(item.urls.small).into(holder.imageCategory)
        holder.imageCategory.setOnClickListener {
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