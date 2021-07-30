package it.kimoterru.walls.model.unsplash


import com.google.gson.annotations.SerializedName

data class PhotoItem(
    @SerializedName("blur_hash")
    val blurHash: String,
    @SerializedName("color")
    val color: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("current_user_collections")
    val currentUserCollections: List<UserCollection>,
    @SerializedName("description")
    val description: String,
    @SerializedName("height")
    val height: Int,
    @SerializedName("id")
    val id: String,
    @SerializedName("liked_by_user")
    val likedByUser: Boolean,
    @SerializedName("likes")
    val likes: Int,
    @SerializedName("links")
    val links: PhotoLinks,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("urls")
    val urls: PhotoUrls,
    @SerializedName("user")
    val user: User,
    @SerializedName("width")
    val width: Int
)