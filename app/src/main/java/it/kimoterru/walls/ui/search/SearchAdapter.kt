package it.kimoterru.walls.ui.search

import android.animation.Animator
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import it.kimoterru.walls.R
import it.kimoterru.walls.domain.models.photo.Photo
import it.kimoterru.walls.ui.widget.AspectRatioImageView
import it.kimoterru.walls.util.Constants.Companion.CROSS_FADE_DURATION
import it.kimoterru.walls.util.WallpaperClickListener
import it.kimoterru.walls.util.gone
import it.kimoterru.walls.util.visible

class SearchAdapter(
    private val listenerWallpaperClick: WallpaperClickListener.WallpaperClick,
    private val listenerLongClick: WallpaperClickListener.LongClick
) : PagingDataAdapter<Photo, SearchAdapter.ViewHolder>(DiffUtilCallBack) {

    override fun getItemViewType(position: Int) = R.layout.card_image_display

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(viewType, parent, false),
            listenerWallpaperClick,
            listenerLongClick
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

    class ViewHolder(
        view: View,
        private val listenerWallpaperClick: WallpaperClickListener.WallpaperClick,
        private val listenerLongClick: WallpaperClickListener.LongClick
    ) : RecyclerView.ViewHolder(view) {

        private val image: AspectRatioImageView = view.findViewById(R.id.card_image_display)
        private val downloadAnim: LottieAnimationView = view.findViewById(R.id.download_anim_view)

        fun bind(item: Photo) {
            image.setAspectRatio(item.width, item.height)

            Glide.with(image).load(item.urls?.thumb)
                .placeholder(ColorDrawable(Color.parseColor(item.color)))
                .transition(DrawableTransitionOptions.withCrossFade(CROSS_FADE_DURATION))
                .into(image).clearOnDetach()

            image.apply {
                setOnClickListener {
                    listenerWallpaperClick.onWallpaperClick(
                        item.id!!,
                        item.id_photo_is_local,
                        item.user?.profileImage?.large!!
                    )
                }
                setOnLongClickListener {
                    downloadAnim.apply {
                        visible()
                        playAnimation()
                        addAnimatorListener(object : Animator.AnimatorListener {
                            override fun onAnimationRepeat(animation: Animator) {}
                            override fun onAnimationCancel(animation: Animator) {}
                            override fun onAnimationStart(animation: Animator) {}
                            override fun onAnimationEnd(animation: Animator) {
                                removeAnimatorListener(this)
                                gone()
                            }
                        })
                    }
                    listenerLongClick.onLongClick(
                        item.id!!,
                        item.links?.download!!
                    )
                    false
                }
            }
        }
    }
}