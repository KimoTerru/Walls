package it.kimoterru.walls.data.remote.models.topic

import com.google.gson.annotations.SerializedName
import it.kimoterru.walls.data.remote.models.photo.PhotoLinksResponse
import it.kimoterru.walls.data.remote.models.photo.PhotoResponse
import it.kimoterru.walls.data.remote.models.photo.UserResponse

data class TopicResponse(
    val id: String,
    val slug: String,
    val title: String,
    val description: String,
    val startsAt: String,
    val updatedAt: String? = null,
    val endsAt: String? = null,
    val featured: Boolean,
    @SerializedName("total_photos")
    val totalPhotos: Int,
    val links: PhotoLinksResponse? = null,
    @SerializedName("cover_photo")
    val coverPhoto: PhotoResponse? = null,
    @SerializedName("preview_photos")
    val previewPhotos: List<PhotoResponse>? = null,
    val status: String? = null,
    val owners: List<UserResponse>? = null,
    @SerializedName("top_contributors")
    val topContributors: UserResponse? = null,
)