package it.kimoterru.walls.repo

import it.kimoterru.walls.models.photo.PhotoItem
import it.kimoterru.walls.models.categories.TopicItem
import it.kimoterru.walls.network.ApiService
import retrofit2.http.Query
import javax.inject.Inject

class WallpaperRepository @Inject constructor(private var service: ApiService) {
    suspend fun getLatestPhotos(
        clientId: String, page: Int,
    ): List<PhotoItem> {
        return service.getLatest(clientId, page)
    }

    suspend fun getTopics(
        clientId: String,
        page: Int,
        per_page: Int,
        order_by: String,
    ): List<TopicItem> {
        return service.getTopicsList(clientId, page, per_page, order_by)
    }

    suspend fun getImageTopics(
        id_or_slug: String,
        clientId: String,
        per_page: Int,
        order_by: String
    ): List<PhotoItem> {
        return service.getTopicImage(id_or_slug, clientId, per_page, order_by)
    }
}