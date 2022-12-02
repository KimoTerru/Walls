package it.kimoterru.walls.data.remote.models.photo

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "photoItem")
data class PhotoResponse(

    @ColumnInfo(name = "id")
    @SerializedName("id")
    val id: String? = null,

    @PrimaryKey(autoGenerate = true)
    var id_photo_is_local: Int,

    @ColumnInfo(name = "created_at")
    @SerializedName("created_at")
    val createdAt: String? = null,

    @ColumnInfo(name = "updated_at")
    @SerializedName("updated_at")
    val updatedAt: String? = null,

    @ColumnInfo(name = "width")
    @SerializedName("width")
    val width: Int? = null,

    @ColumnInfo(name = "height")
    @SerializedName("height")
    val height: Int? = null,

    @ColumnInfo(name = "color")
    @SerializedName("color")
    val color: String? = null,

    @ColumnInfo(name = "downloads")
    @SerializedName("downloads")
    val downloads: Int? = null,

    @ColumnInfo(name = "blur_hash")
    @SerializedName("blur_hash")
    val blurHash: String? = null,

    @ColumnInfo(name = "likes")
    @SerializedName("likes")
    val likes: Int? = null,

    @ColumnInfo(name = "liked_by_user")
    @SerializedName("liked_by_user")
    val likedByUser: Boolean? = null,

    @ColumnInfo(name = "publicDomain")
    @SerializedName("public_domain")
    val publicDomain: Boolean? = null,

    @ColumnInfo(name = "description")
    @SerializedName("description")
    val description: String? = null,

    @ColumnInfo(name = "altDescription")
    @SerializedName("alt_description")
    val altDescription: String? = null,

    @Embedded
    @SerializedName("exif")
    val exif: ExifResponse? = null,

    @Embedded
    @SerializedName("location")
    val location: LocationResponse? = null,

    /*@SerializedName("tags")
    val tags: List<TagsResponse>? = null,*/

    /* @Embedded
     @SerializedName("current_user_collections")
     val currentUserCollections: List<UserCollectionResponse>,*/

    @Embedded
    @SerializedName("urls")
    val urls: PhotoUrlsResponse? = null,

    @Embedded
    @SerializedName("links")
    val links: PhotoLinksResponse? = null,

    @Embedded
    @SerializedName("user")
    val user: UserResponse? = null,
)