package it.kimoterru.walls.domain.usecase.detailI

import it.kimoterru.walls.domain.models.photo.Photo
import it.kimoterru.walls.domain.repository.WallpaperRepository
import javax.inject.Inject

class DeletePhotoUseCase @Inject constructor(
    private val repository: WallpaperRepository
) {

    suspend fun invoke(
        photo: Photo
    ) {
        return repository.deletePhoto(photo)
    }
}