package it.kimoterru.walls.domain.usecase.detailIage

import it.kimoterru.walls.data.remote.models.photo.PhotoItem
import it.kimoterru.walls.domain.repository.WallpaperRepository
import javax.inject.Inject

class DeletePhotoUseCase @Inject constructor(
    private val repository: WallpaperRepository
) {

    suspend fun invoke(
        photo: PhotoItem
    ) {
        return repository.deletePhoto(photo)
    }
}