package it.kimoterru.walls.domain.usecase.detailIage

import it.kimoterru.walls.data.remote.models.photo.PhotoItem
import it.kimoterru.walls.domain.repository.WallpaperRepository
import javax.inject.Inject

class GetPhotoFromApiByIDUseCase @Inject constructor(
    private val repository: WallpaperRepository
) {

    suspend fun invoke(
        id: String,
        clientId: String
    ): PhotoItem {
        return repository.getPhotoFromApiByID(id, clientId)
    }
}