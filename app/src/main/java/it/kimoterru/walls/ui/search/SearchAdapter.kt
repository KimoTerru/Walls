package it.kimoterru.walls.ui.search

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
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
) : PagingDataAdapter<Photo, SearchAdapter.ViewHolder>(DiffUtilCallBack) {

    override fun getItemViewType(position: Int) = R.layout.card_image_display

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(viewType, parent, false), listener
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    object DiffUtilCallBack : DiffUtil.ItemCallback<Photo>() {
        override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return oldItem == newItem
        }
    }

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