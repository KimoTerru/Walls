package it.kimoterru.walls.repo

import it.kimoterru.walls.models.PhotoItem
import it.kimoterru.walls.network.ApiService
import javax.inject.Inject

class WallpaperRepository @Inject constructor(private var service: ApiService) {
    suspend fun getLatestPhotos(
        clientId: String, page: Int,
    ): List<PhotoItem> {
        return service.getLatest(clientId, page)
    }

    suspend fun getTopPhotos(
        clientId: String,
        page: Int,
        per_page: Int,
        order_by: String,
    ): List<PhotoItem> {
        return service.getTopList(clientId, page, per_page, order_by)
    }
}