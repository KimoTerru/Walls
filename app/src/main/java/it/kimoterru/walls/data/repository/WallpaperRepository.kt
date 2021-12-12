package it.kimoterru.walls.data.repository

import it.kimoterru.walls.data.database.PhotoDao
import it.kimoterru.walls.data.models.categories.TopicItem
import it.kimoterru.walls.data.models.photo.PhotoItem
import it.kimoterru.walls.data.models.search.SearchItem
import it.kimoterru.walls.data.ApiService
import javax.inject.Inject

class WallpaperRepository @Inject constructor(
    private var service: ApiService,
    private val photoDao: PhotoDao
) {

    suspend fun getLatestPhotos(
        clientId: String, page: Int,
    ): List<PhotoItem> {
        return service.getLatest(clientId, page)
    }

    suspend fun getPhoto(id: String, clientId: String): PhotoItem {
        return service.getPhoto(id, clientId)
    }

    suspend fun getTopics(
        clientId: String, page: Int, per_page: Int, order_by: String,
    ): List<TopicItem> {
        return service.getTopicsList(clientId, page, per_page, order_by)
    }

    suspend fun getImageTopics(
        id_or_slug: String, clientId: String, per_page: Int, order_by: String
    ): List<PhotoItem> {
        return service.getTopicImage(id_or_slug, clientId, per_page, order_by)
    }

    suspend fun getImageColors(
        query: String, color: String, clientId: String, per_page: Int, order_by: String
    ): SearchItem {
        return service.getColorImage(query, color, clientId, per_page, order_by)
    }

    suspend fun getImageSearch(
        query: String, clientId: String, per_page: Int, order_by: String,
    ): SearchItem {
        return service.getSearchImage(query, clientId, per_page, order_by)
    }

    suspend fun getAllPhotosFromFavorite(): List<PhotoItem> {
        return photoDao.getAllPhoto()
    }

    suspend fun getPhotoFromFavoriteByID(id: Int): PhotoItem? {
        return photoDao.getById(id)
    }
}