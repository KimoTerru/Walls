package it.kimoterru.walls.domain.usecase.home

import it.kimoterru.walls.data.remote.models.photo.PhotoResponse
import it.kimoterru.walls.domain.repository.WallpaperRepository
import javax.inject.Inject

class GetLatestPhotosUseCase @Inject constructor(
    private val repository: WallpaperRepository
) {

    suspend fun invoke(
        clientId: String,
        page: Int
    ): List<PhotoResponse> {
        return repository.getLatestPhotos(clientId, page)
    }
}