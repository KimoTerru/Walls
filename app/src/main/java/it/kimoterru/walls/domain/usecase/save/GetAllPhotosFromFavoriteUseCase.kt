package it.kimoterru.walls.domain.usecase.save

import it.kimoterru.walls.data.remote.models.photo.PhotoResponse
import it.kimoterru.walls.domain.repository.WallpaperRepository
import javax.inject.Inject

class GetAllPhotosFromFavoriteUseCase @Inject constructor(
    private val repository: WallpaperRepository
) {

    suspend fun invoke(): List<PhotoResponse> {
        return repository.getAllPhotosFromFavorite()
    }
}