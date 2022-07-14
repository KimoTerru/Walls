package it.kimoterru.walls.domain.usecase.detailIage

import it.kimoterru.walls.data.remote.models.photo.PhotoItem
import it.kimoterru.walls.domain.repository.WallpaperRepository
import javax.inject.Inject

class GetPhotoFromFavoriteByIDUseCase @Inject constructor(
    private val repository: WallpaperRepository
) {

    suspend fun invoke(
        id_photo: Int
    ): PhotoItem? {
        return repository.getPhotoFromFavoriteByID(id_photo)
    }
}