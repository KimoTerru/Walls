package it.kimoterru.walls.data.remote.models.topic

import com.google.gson.annotations.SerializedName
import it.kimoterru.walls.data.remote.models.photo.PhotoResponse

data class TopicResponse(
    var id: String,
    var slug: String,
    var title: String,
    var description: String,
    var featured: Boolean,
    @SerializedName("total_photos")
    var totalPhotos: Int,
    @SerializedName("cover_photo")
    var coverPhoto: PhotoResponse?
)