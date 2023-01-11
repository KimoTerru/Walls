package it.kimoterru.walls.data.repository

import it.kimoterru.walls.data.local.WallpaperDao
import it.kimoterru.walls.data.mapper.toPhoto
import it.kimoterru.walls.data.mapper.toPhotoEntity
import it.kimoterru.walls.data.mapper.toSearchPhoto
import it.kimoterru.walls.data.mapper.toTopic
import it.kimoterru.walls.data.remote.WallpaperService
import it.kimoterru.walls.domain.models.photo.Photo
import it.kimoterru.walls.domain.models.search.SearchPhoto
import it.kimoterru.walls.domain.models.topic.Topic
import it.kimoterru.walls.domain.repository.WallpaperRepository
import javax.inject.Inject

class WallpaperRepositoryImpl @Inject constructor(
    private var wallpaperService: WallpaperService,
    private val wallpaperDao: WallpaperDao
) : WallpaperRepository {

    override suspend fun getLatestPhotos(
        clientId: String,
        page: Int,
    ): List<Photo> {
        return wallpaperService.getLatest(clientId, page).map { it.toPhoto() }
    }

    override suspend fun getPhotoFromApiByID(
        id: String,
        clientId: String
    ): Photo {
        return wallpaperService.getPhoto(id, clientId).toPhoto()
    }

    override suspend fun getTopics(
        clientId: String,
        page: Int,
        per_page: Int,
        order_by: String,
    ): List<Topic> {
        return wallpaperService.getTopicsList(clientId, page, per_page, order_by)
            .map { it.toTopic() }
    }

    override suspend fun getImageTopics(
        id_or_slug: String,
        clientId: String,
        page: Int,
        per_page: Int,
        order_by: String
    ): List<Photo> {
        return wallpaperService.getTopicImage(id_or_slug, clientId, page, per_page, order_by)
            .map { it.toPhoto() }
    }

    override suspend fun getImageColors(
        query: String,
        color: String,
        clientId: String,
        page: Int,
        per_page: Int,
        order_by: String
    ): SearchPhoto {
        return wallpaperService.getColorImage(query, color, clientId, page, per_page, order_by)
            .toSearchPhoto()
    }

    override suspend fun getImageSearch(
        query: String,
        clientId: String,
        page: Int,
        per_page: Int,
        order_by: String,
    ): SearchPhoto {
        return wallpaperService.getSearchImage(query, clientId, page, per_page, order_by)
            .toSearchPhoto()
    }

    override suspend fun getAllPhotosFromFavorite(): List<Photo> {
        return wallpaperDao.getAllPhoto().map { it.toPhoto() }
    }

    override suspend fun getPhotoFromFavoriteByID(id: Int): Photo? {
        return wallpaperDao.getById(id)?.toPhoto()
    }

    override suspend fun insertPhoto(data: Photo) {
        return wallpaperDao.insertPhoto(data.toPhotoEntity())
    }

    override suspend fun deletePhoto(data: Photo) {
        return wallpaperDao.deletePhoto(data.toPhotoEntity())
    }
}