package it.kimoterru.walls.ui.home

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import it.kimoterru.walls.R
import it.kimoterru.walls.data.remote.models.topic.TopicResponse
import it.kimoterru.walls.util.Constants.Companion.CROSS_FADE_DURATION
import it.kimoterru.walls.util.WallpaperClickListener

class TopicAdapter(
    private val topicData: List<TopicResponse>,
    private val listener: WallpaperClickListener.HomeFragment,
) : RecyclerView.Adapter<TopicAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val categoriesView: ImageView = view.findViewById(R.id.card_categories)
        val nameView: TextView = view.findViewById(R.id.name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.card_topic, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = topicData[position]
        Glide.with(holder.categoriesView)
            .load(item.coverPhoto?.urls?.thumb)
            .transition(DrawableTransitionOptions.withCrossFade(CROSS_FADE_DURATION))
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?, model: Any?, target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    e?.printStackTrace()
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?, model: Any?, target: Target<Drawable>?,
                    dataSource: DataSource?, isFirstResource: Boolean
                ): Boolean {
                    return false
                }
            })
            .into(holder.categoriesView) // FIXME: 03.02.2022
            .clearOnDetach()
        holder.nameView.text = item.title
        holder.categoriesView.setOnClickListener {
            listener.onTopicClick(item.slug, item.title, item.totalPhotos)
        }
    }

    override fun getItemCount() = topicData.size
}