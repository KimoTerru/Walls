package it.kimoterru.walls.data.repository

import it.kimoterru.walls.data.local.WallpaperDao
import it.kimoterru.walls.data.remote.WallpaperService
import it.kimoterru.walls.data.remote.models.topic.TopicResponse
import it.kimoterru.walls.data.remote.models.photo.PhotoResponse
import it.kimoterru.walls.data.remote.models.search.SearchResponse
import it.kimoterru.walls.domain.repository.WallpaperRepository
import javax.inject.Inject

class WallpaperRepositoryImpl @Inject constructor(
    private var wallpaperService: WallpaperService,
    private val wallpaperDao: WallpaperDao
) : WallpaperRepository {

    override suspend fun getLatestPhotos(
        clientId: String,
        page: Int,
    ): List<PhotoResponse> {
        return wallpaperService.getLatest(clientId, page)
    }

    override suspend fun getPhotoFromApiByID(
        id: String,
        clientId: String
    ): PhotoResponse {
        return wallpaperService.getPhoto(id, clientId)
    }

    override suspend fun getTopics(
        clientId: String,
        page: Int,
        per_page: Int,
        order_by: String,
    ): List<TopicResponse> {
        return wallpaperService.getTopicsList(clientId, page, per_page, order_by)
    }

    override suspend fun getImageTopics(
        id_or_slug: String,
        clientId: String,
        page: Int,
        per_page: Int,
        order_by: String
    ): List<PhotoResponse> {
        return wallpaperService.getTopicImage(id_or_slug, clientId, page, per_page, order_by)
    }

    override suspend fun getImageColors(
        query: String,
        color: String,
        clientId: String,
        page: Int,
        per_page: Int,
        order_by: String
    ): SearchResponse {
        return wallpaperService.getColorImage(query, color, clientId, page, per_page, order_by)
    }

    override suspend fun getImageSearch(
        query: String,
        clientId: String,
        page: Int,
        per_page: Int,
        order_by: String,
    ): SearchResponse {
        return wallpaperService.getSearchImage(query, clientId, page, per_page, order_by)
    }

    override suspend fun getAllPhotosFromFavorite(): List<PhotoResponse> {
        return wallpaperDao.getAllPhoto()
    }

    override suspend fun getPhotoFromFavoriteByID(id: Int): PhotoResponse? {
        return wallpaperDao.getById(id)
    }

    override suspend fun insertPhoto(data: PhotoResponse) {
        return wallpaperDao.insertPhoto(data)
    }

    override suspend fun deletePhoto(data: PhotoResponse) {
        return wallpaperDao.deletePhoto(data)
    }
}