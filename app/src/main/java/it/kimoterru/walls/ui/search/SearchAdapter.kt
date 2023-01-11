package it.kimoterru.walls.ui.search

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import it.kimoterru.walls.R
import it.kimoterru.walls.domain.models.photo.Photo
import it.kimoterru.walls.ui.widget.AspectRatioImageView
import it.kimoterru.walls.util.Constants.Companion.CROSS_FADE_DURATION
import it.kimoterru.walls.util.WallpaperClickListener

class SearchAdapter(
    private val listener: WallpaperClickListener.WallpaperClick
) : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    private var data = arrayListOf<Photo>()

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

    fun addData(newData: List<Photo>) {
        data.addAll(newData)
        notifyDataSetChanged()
    }

    fun updateItems(updateData: List<Photo>) {
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

        private val image: AspectRatioImageView = view.findViewById(R.id.card_image_display)

        fun bind(item: Photo) {
            image.setAspectRatio(item.width, item.height)

            Glide.with(image).load(item.urls?.thumb)
                .placeholder(ColorDrawable(Color.parseColor(item.color)))
                .transition(DrawableTransitionOptions.withCrossFade(CROSS_FADE_DURATION))
                .into(image).clearOnDetach()

            image.setOnClickListener {
                listener.onWallpaperClick(
                    item.id!!,
                    item.id_photo_is_local,
                    item.user?.profileImage?.large!!
                )
            }
        }
    }
}