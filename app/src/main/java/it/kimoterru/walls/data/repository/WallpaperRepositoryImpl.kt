package it.kimoterru.walls.data.repository

import it.kimoterru.walls.data.local.WallpaperDao
import it.kimoterru.walls.data.remote.WallpaperService
import it.kimoterru.walls.data.remote.models.categories.TopicItem
import it.kimoterru.walls.data.remote.models.photo.PhotoItem
import it.kimoterru.walls.data.remote.models.search.SearchItem
import it.kimoterru.walls.domain.repository.WallpaperRepository
import javax.inject.Inject

class WallpaperRepositoryImpl @Inject constructor(
    private var wallpaperService: WallpaperService,
    private val wallpaperDao: WallpaperDao
) : WallpaperRepository {

    override suspend fun getLatestPhotos(
        clientId: String,
        page: Int,
    ): List<PhotoItem> {
        return wallpaperService.getLatest(clientId, page)
    }

    override suspend fun getPhotoFromApiByID(
        id: String,
        clientId: String
    ): PhotoItem {
        return wallpaperService.getPhoto(id, clientId)
    }

    override suspend fun getTopics(
        clientId: String,
        page: Int,
        per_page: Int,
        order_by: String,
    ): List<TopicItem> {
        return wallpaperService.getTopicsList(clientId, page, per_page, order_by)
    }

    override suspend fun getImageTopics(
        id_or_slug: String,
        clientId: String,
        page: Int,
        per_page: Int,
        order_by: String
    ): List<PhotoItem> {
        return wallpaperService.getTopicImage(id_or_slug, clientId, page, per_page, order_by)
    }

    override suspend fun getImageColors(
        query: String,
        color: String,
        clientId: String,
        page: Int,
        per_page: Int,
        order_by: String
    ): SearchItem {
        return wallpaperService.getColorImage(query, color, clientId, page, per_page, order_by)
    }

    override suspend fun getImageSearch(
        query: String,
        clientId: String,
        page: Int,
        per_page: Int,
        order_by: String,
    ): SearchItem {
        return wallpaperService.getSearchImage(query, clientId, page, per_page, order_by)
    }

    override suspend fun getAllPhotosFromFavorite(): List<PhotoItem> {
        return wallpaperDao.getAllPhoto()
    }

    override suspend fun getPhotoFromFavoriteByID(id: Int): PhotoItem? {
        return wallpaperDao.getById(id)
    }

    override suspend fun insertPhoto(data: PhotoItem) {
        return wallpaperDao.insertPhoto(data)
    }

    override suspend fun deletePhoto(data: PhotoItem) {
        return wallpaperDao.deletePhoto(data)
    }
}