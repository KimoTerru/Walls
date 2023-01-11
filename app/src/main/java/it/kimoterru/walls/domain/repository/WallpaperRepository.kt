package it.kimoterru.walls.domain.repository

import it.kimoterru.walls.domain.models.topic.Topic
import it.kimoterru.walls.domain.models.photo.Photo
import it.kimoterru.walls.domain.models.search.SearchPhoto

interface WallpaperRepository {

    suspend fun getLatestPhotos(
        clientId: String,
        page: Int,
    ): List<Photo>

    suspend fun getPhotoFromApiByID(
        id: String,
        clientId: String
    ): Photo

    suspend fun getTopics(
        clientId: String,
        page: Int,
        per_page: Int,
        order_by: String,
    ): List<Topic>

    suspend fun getImageTopics(
        id_or_slug: String,
        clientId: String,
        page: Int,
        per_page: Int,
        order_by: String
    ): List<Photo>

    suspend fun getImageColors(
        query: String,
        color: String,
        clientId: String,
        page: Int,
        per_page: Int,
        order_by: String
    ): SearchPhoto

    suspend fun getImageSearch(
        query: String,
        clientId: String,
        page: Int,
        per_page: Int,
        order_by: String,
    ): SearchPhoto

    suspend fun getAllPhotosFromFavorite(): List<Photo>

    suspend fun getPhotoFromFavoriteByID(id: Int): Photo?

    suspend fun insertPhoto(data: Photo)

    suspend fun deletePhoto(data: Photo)
}