package it.kimoterru.walls.data.local.model.photo

import androidx.room.ColumnInfo
import androidx.room.Embedded

data class UserEntity(

    @ColumnInfo(name = "id_user")
    val id: String? = null,

    @ColumnInfo(name = "updated_at_user")
    val updatedAt: String? = null,

    @ColumnInfo(name = "username_user")
    val username: String? = null,

    @ColumnInfo(name = "name_user")
    val name: String? = null,

    @ColumnInfo(name = "firstName_user")
    val firstName: String? = null,

    @ColumnInfo(name = "lastName_user")
    val lastName: String? = null,

    @Embedded
    val profileImage: ProfileImageEntity? = null,

    @ColumnInfo(name = "portfolio_url_user")
    val portfolioUrl: String? = null,

    @ColumnInfo(name = "bio_user")
    val bio: String? = null,

    @ColumnInfo(name = "location_user")
    val location: String? = null,

    @ColumnInfo(name = "total_likes_user")
    val totalLikes: Int? = null,

    @ColumnInfo(name = "total_photos_user")
    val totalPhotos: Int? = null,

    @ColumnInfo(name = "acceptedTos_user")
    val acceptedTos: Boolean? = null,

    @ColumnInfo(name = "total_collections_user")
    val totalCollections: Int? = null,

    @ColumnInfo(name = "instagram_username_user")
    val instagramUsername: String? = null,

    @ColumnInfo(name = "twitter_username_user")
    val twitterUsername: String? = null,

    @Embedded
    val links: UserLinksEntity? = null,
)