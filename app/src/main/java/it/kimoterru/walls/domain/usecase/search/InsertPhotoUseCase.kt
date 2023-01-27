package it.kimoterru.walls.domain.usecase.search

import it.kimoterru.walls.domain.models.photo.Photo
import it.kimoterru.walls.domain.repository.WallpaperRepository
import javax.inject.Inject

class InsertPhotoUseCase @Inject constructor(
    private val repository: WallpaperRepository
) {

    suspend fun invoke(
        photo: Photo
    ) {
        return repository.insertPhoto(photo)
    }
}