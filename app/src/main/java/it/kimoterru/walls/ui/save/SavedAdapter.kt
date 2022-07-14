package it.kimoterru.walls.ui.save

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

class SavedAdapter(
    private val listener: WallpaperClickListener.WallpaperClick,
) : RecyclerView.Adapter<SavedAdapter.ViewHolder>() {

    private var data = mutableListOf<PhotoItem>()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.card_image_display)
    }

    override fun getItemViewType(position: Int) = R.layout.card_image_display

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]

        holder.image.measure(item.width, item.height)
        Glide.with(holder.image).load(item.urls.small)
            .placeholder(PlaceHolderDrawableHelper.getBackgroundDrawable(position, item.color))
            .into(holder.image)

        holder.image.setOnClickListener {
            listener.onWallpaperClick(item.id, item.user.profileImage.large, item.id_photo)
        }
    }

    fun updateItems(updateData: List<PhotoItem>) {
        data.clear()
        data.addAll(updateData)
        notifyDataSetChanged()
    }

    override fun getItemCount() = data.size
}