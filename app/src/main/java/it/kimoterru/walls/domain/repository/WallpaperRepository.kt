package it.kimoterru.walls.domain.repository

import it.kimoterru.walls.data.remote.models.topic.TopicResponse
import it.kimoterru.walls.data.remote.models.photo.PhotoResponse
import it.kimoterru.walls.data.remote.models.search.SearchResponse

interface WallpaperRepository {

    suspend fun getLatestPhotos(
        clientId: String,
        page: Int,
    ): List<PhotoResponse>

    suspend fun getPhotoFromApiByID(
        id: String,
        clientId: String
    ): PhotoResponse

    suspend fun getTopics(
        clientId: String,
        page: Int,
        per_page: Int,
        order_by: String,
    ): List<TopicResponse>

    suspend fun getImageTopics(
        id_or_slug: String,
        clientId: String,
        page: Int,
        per_page: Int,
        order_by: String
    ): List<PhotoResponse>

    suspend fun getImageColors(
        query: String,
        color: String,
        clientId: String,
        page: Int,
        per_page: Int,
        order_by: String
    ): SearchResponse

    suspend fun getImageSearch(
        query: String,
        clientId: String,
        page: Int,
        per_page: Int,
        order_by: String,
    ): SearchResponse

    suspend fun getAllPhotosFromFavorite(): List<PhotoResponse>

    suspend fun getPhotoFromFavoriteByID(id: Int): PhotoResponse?

    suspend fun insertPhoto(data: PhotoResponse)

    suspend fun deletePhoto(data: PhotoResponse)
}