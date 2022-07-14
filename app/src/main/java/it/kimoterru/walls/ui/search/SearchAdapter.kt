package it.kimoterru.walls.ui.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import it.kimoterru.walls.R
import it.kimoterru.walls.util.WallpaperClickListener
import it.kimoterru.walls.data.remote.models.photo.PhotoItem
import it.kimoterru.walls.util.PlaceHolderDrawableHelper

class SearchAdapter(
    private val listener: WallpaperClickListener.WallpaperClick
) : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    private var data = mutableListOf<PhotoItem>()

    override fun getItemViewType(position: Int) = R.layout.card_image_display

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(viewType, parent, false), listener
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }

    fun addData(newData: List<PhotoItem>) {
        data.addAll(newData)
        notifyDataSetChanged()
    }

    fun updateItems(updateData: List<PhotoItem>) {
        data.clear()
        data.addAll(updateData)
        notifyDataSetChanged()
    }

    fun clearData() {
        data.clear()
        notifyDataSetChanged()
    }

    override fun getItemCount() = data.size

    class ViewHolder(view: View, private val listener: WallpaperClickListener.WallpaperClick) :
        RecyclerView.ViewHolder(view) {

        private val image: ImageView = view.findViewById(R.id.card_image_display)

        fun bind(item: PhotoItem) {
            image.measure(item.width, item.height)

            Glide.with(image).load(item.urls.small)
                .placeholder(PlaceHolderDrawableHelper.getBackgroundDrawable(position, item.color))
                .into(image)

            image.setOnClickListener {
                listener.onWallpaperClick(item.id, item.user.profileImage.large, item.id_photo)
            }
        }
    }
}