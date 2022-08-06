package it.kimoterru.walls.domain.usecase.detailI

import it.kimoterru.walls.data.remote.models.photo.PhotoResponse
import it.kimoterru.walls.domain.repository.WallpaperRepository
import javax.inject.Inject

class GetPhotoFromApiByIDUseCase @Inject constructor(
    private val repository: WallpaperRepository
) {

    suspend fun invoke(
        id: String,
        clientId: String
    ): PhotoResponse {
        return repository.getPhotoFromApiByID(id, clientId)
    }
}