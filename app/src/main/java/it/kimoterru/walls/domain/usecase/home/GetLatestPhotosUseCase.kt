package it.kimoterru.walls.domain.usecase.home

import it.kimoterru.walls.data.remote.models.photo.PhotoItem
import it.kimoterru.walls.domain.repository.WallpaperRepository
import javax.inject.Inject

class GetLatestPhotosUseCase @Inject constructor(
    private val repository: WallpaperRepository
) {

    suspend fun invoke(
        clientId: String,
        page: Int
    ): List<PhotoItem> {
        return repository.getLatestPhotos(clientId, page)
    }
}