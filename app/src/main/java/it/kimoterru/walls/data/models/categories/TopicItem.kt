package it.kimoterru.walls.data.models.categories

import com.google.gson.annotations.SerializedName
import it.kimoterru.walls.data.models.photo.PhotoItem

data class TopicItem(
    var id: String,
    var slug: String,
    var title: String,
    var description: String,
    var featured: Boolean,
    @SerializedName("total_photos")
    var totalPhotos: Int,
    @SerializedName("cover_photo")
    var coverPhoto: PhotoItem?
)