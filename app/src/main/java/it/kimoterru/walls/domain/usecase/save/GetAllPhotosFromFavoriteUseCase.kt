package it.kimoterru.walls.domain.usecase.save

import it.kimoterru.walls.domain.models.photo.Photo
import it.kimoterru.walls.domain.repository.WallpaperRepository
import javax.inject.Inject

class GetAllPhotosFromFavoriteUseCase @Inject constructor(
    private val repository: WallpaperRepository
) {

    suspend fun invoke(): List<Photo> {
        return repository.getAllPhotosFromFavorite()
    }
}