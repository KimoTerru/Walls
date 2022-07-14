package it.kimoterru.walls.domain.repository

import it.kimoterru.walls.data.remote.models.categories.TopicItem
import it.kimoterru.walls.data.remote.models.photo.PhotoItem
import it.kimoterru.walls.data.remote.models.search.SearchItem

interface WallpaperRepository {

    suspend fun getLatestPhotos(
        clientId: String,
        page: Int,
    ): List<PhotoItem>

    suspend fun getPhotoFromApiByID(
        id: String,
        clientId: String
    ): PhotoItem

    suspend fun getTopics(
        clientId: String,
        page: Int,
        per_page: Int,
        order_by: String,
    ): List<TopicItem>

    suspend fun getImageTopics(
        id_or_slug: String,
        clientId: String,
        page: Int,
        per_page: Int,
        order_by: String
    ): List<PhotoItem>

    suspend fun getImageColors(
        query: String,
        color: String,
        clientId: String,
        page: Int,
        per_page: Int,
        order_by: String
    ): SearchItem

    suspend fun getImageSearch(
        query: String,
        clientId: String,
        page: Int,
        per_page: Int,
        order_by: String,
    ): SearchItem

    suspend fun getAllPhotosFromFavorite(): List<PhotoItem>

    suspend fun getPhotoFromFavoriteByID(id: Int): PhotoItem?

    suspend fun insertPhoto(data: PhotoItem)

    suspend fun deletePhoto(data: PhotoItem)
}