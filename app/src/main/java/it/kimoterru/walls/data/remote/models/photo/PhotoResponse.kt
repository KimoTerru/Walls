package it.kimoterru.walls.data.remote.models.photo

import com.google.gson.annotations.SerializedName

data class PhotoResponse(

    @SerializedName("id")
    val id: String? = null,

    var id_photo_is_local: Int,

    @SerializedName("created_at")
    val createdAt: String? = null,

    @SerializedName("updated_at")
    val updatedAt: String? = null,

    @SerializedName("width")
    val width: Int? = null,

    @SerializedName("height")
    val height: Int? = null,

    @SerializedName("color")
    val color: String? = null,

    @SerializedName("downloads")
    val downloads: Int? = null,

    @SerializedName("blur_hash")
    val blurHash: String? = null,

    @SerializedName("likes")
    val likes: Int? = null,

    @SerializedName("liked_by_user")
    val likedByUser: Boolean? = null,

    @SerializedName("public_domain")
    val publicDomain: Boolean? = null,

    @SerializedName("description")
    val description: String? = null,

    @SerializedName("alt_description")
    val altDescription: String? = null,

    @SerializedName("exif")
    val exif: ExifResponse? = null,

    @SerializedName("location")
    val location: LocationResponse? = null,

    @SerializedName("urls")
    val urls: PhotoUrlsResponse? = null,

    @SerializedName("links")
    val links: PhotoLinksResponse? = null,

    @SerializedName("user")
    val user: UserResponse? = null,
)