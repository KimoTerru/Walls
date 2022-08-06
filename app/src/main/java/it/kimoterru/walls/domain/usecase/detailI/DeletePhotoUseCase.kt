package it.kimoterru.walls.domain.usecase.detailI

import it.kimoterru.walls.data.remote.models.photo.PhotoResponse
import it.kimoterru.walls.domain.repository.WallpaperRepository
import javax.inject.Inject

class DeletePhotoUseCase @Inject constructor(
    private val repository: WallpaperRepository
) {

    suspend fun invoke(
        photo: PhotoResponse
    ) {
        return repository.deletePhoto(photo)
    }
}