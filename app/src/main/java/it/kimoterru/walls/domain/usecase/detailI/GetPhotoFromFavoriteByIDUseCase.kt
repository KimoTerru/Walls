package it.kimoterru.walls.domain.usecase.detailI

import it.kimoterru.walls.data.remote.models.photo.PhotoResponse
import it.kimoterru.walls.domain.repository.WallpaperRepository
import javax.inject.Inject

class GetPhotoFromFavoriteByIDUseCase @Inject constructor(
    private val repository: WallpaperRepository
) {

    suspend fun invoke(
        id_photo: Int
    ): PhotoResponse? {
        return repository.getPhotoFromFavoriteByID(id_photo)
    }
}