package it.kimoterru.walls.data.remote.models.photo

import com.google.gson.annotations.SerializedName

data class UserCollectionResponse(
    @SerializedName("cover_photo")
    val coverPhoto: Any? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("published_at")
    val publishedAt: String? = null,
    @SerializedName("updated_at")
    val updatedAt: String? = null,
    @SerializedName("last_collected_at")
    val lastCollectedAt: String? = null,
    @SerializedName("user")
    val user: Any? = null
)