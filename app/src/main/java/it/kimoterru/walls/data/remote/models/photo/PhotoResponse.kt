package it.kimoterru.walls.data.remote.models.photo

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "photoItem")
data class PhotoResponse(
    @PrimaryKey(autoGenerate = true)
    var id_photo: Int,

    @ColumnInfo(name = "blur_hash")
    @SerializedName("blur_hash")
    val blurHash: String,

    @ColumnInfo(name = "color")
    @SerializedName("color")
    val color: String,

    @ColumnInfo(name = "created_at")
    @SerializedName("created_at")
    val createdAt: String,

    @ColumnInfo(name = "downloads")
    @SerializedName("downloads")
    val downloads: Int,

    /* @Embedded
     @SerializedName("current_user_collections")
     val currentUserCollections: List<UserCollectionResponse>,*/

    @ColumnInfo(name = "description")
    @SerializedName("description")
    val description: String? = null,

    @Embedded
    @SerializedName("exif")
    val exif: ExifResponse? = null,

    @ColumnInfo(name = "height")
    @SerializedName("height")
    val height: Int,

    @ColumnInfo(name = "id")
    @SerializedName("id")
    val id: String,

    @ColumnInfo(name = "liked_by_user")
    @SerializedName("liked_by_user")
    val likedByUser: Boolean,

    @ColumnInfo(name = "likes")
    @SerializedName("likes")
    val likes: Int? = null,

    @Embedded
    @SerializedName("links")
    val links: PhotoLinksResponse,

    @Embedded
    @SerializedName("location")
    val location: LocationResponse? = null,

    @ColumnInfo(name = "updated_at")
    @SerializedName("updated_at")
    val updatedAt: String,

    @Embedded
    @SerializedName("urls")
    val urls: PhotoUrlsResponse,

    @Embedded
    @SerializedName("user")
    val user: UserResponse,

    @ColumnInfo(name = "width")
    @SerializedName("width")
    val width: Int
)