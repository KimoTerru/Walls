package it.kimoterru.walls.domain.usecase.detailI

import it.kimoterru.walls.domain.models.photo.Photo
import it.kimoterru.walls.domain.repository.WallpaperRepository
import javax.inject.Inject

class GetPhotoFromFavoriteByIDUseCase @Inject constructor(
    private val repository: WallpaperRepository
) {

    suspend fun invoke(
        id_photo: Int
    ): Photo? {
        return repository.getPhotoFromFavoriteByID(id_photo)
    }
}