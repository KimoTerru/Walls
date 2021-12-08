package it.kimoterru.walls.models.photo

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "photoItem")
data class PhotoItem(
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
     val currentUserCollections: List<UserCollection>,*/

    /*@ColumnInfo(name = "description")
    @SerializedName("description")
    val description: String,*/

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
    val likes: Int,

    @Embedded
    @SerializedName("links")
    val links: PhotoLinks,

    /*@Embedded
    @SerializedName("location")
    val location: Location,*/

    @ColumnInfo(name = "updated_at")
    @SerializedName("updated_at")
    val updatedAt: String,

    @Embedded
    @SerializedName("urls")
    val urls: PhotoUrls,

    @Embedded
    @SerializedName("user")
    val user: User,

    @ColumnInfo(name = "width")
    @SerializedName("width")
    val width: Int
)