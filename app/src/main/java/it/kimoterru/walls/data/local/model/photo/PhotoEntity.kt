package it.kimoterru.walls.data.local.model.photo

import androidx.room.*

@Entity(tableName = "photoItem", indices = [Index(value = ["id"], unique = true)])
data class PhotoEntity(

    @ColumnInfo(name = "id")
    val id: String? = null,

    @PrimaryKey(autoGenerate = true)
    val id_photo_is_local: Int = 0,

    @ColumnInfo(name = "created_at")
    val createdAt: String? = null,

    @ColumnInfo(name = "updated_at")
    val updatedAt: String? = null,

    @ColumnInfo(name = "width")
    val width: Int? = null,

    @ColumnInfo(name = "height")
    val height: Int? = null,

    @ColumnInfo(name = "color")
    val color: String? = null,

    @ColumnInfo(name = "downloads")
    val downloads: Int? = null,

    @ColumnInfo(name = "blur_hash")
    val blurHash: String? = null,

    @ColumnInfo(name = "likes")
    val likes: Int? = null,

    @ColumnInfo(name = "liked_by_user")
    val likedByUser: Boolean? = null,

    @ColumnInfo(name = "publicDomain")
    val publicDomain: Boolean? = null,

    @ColumnInfo(name = "description")
    val description: String? = null,

    @ColumnInfo(name = "altDescription")
    val altDescription: String? = null,

    @Embedded
    val exif: ExifEntity? = null,

    @Embedded
    val location: LocationEntity? = null,

    /*val tags: List<TagsResponse>? = null,*/

    /* @Embedded
     val currentUserCollections: List<UserCollectionEntity>,*/

    @Embedded
    val urls: PhotoUrlsEntity? = null,

    @Embedded
    val links: PhotoLinksEntity? = null,

    @Embedded
    val user: UserEntity? = null,
)