package it.kimoterru.walls.repo

import it.kimoterru.walls.model.unsplash.PhotoItem
import it.kimoterru.walls.network.ApiService
import javax.inject.Inject

class WallpaperRepository @Inject constructor(private var service: ApiService) {

    suspend fun getLatestPhotos(
        clientId: String, page: Int
    ): List<PhotoItem> {
        return service.getLatest(clientId, page)
    }
}