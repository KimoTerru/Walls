package it.kimoterru.walls.domain.usecase.home

import it.kimoterru.walls.domain.models.photo.Photo
import it.kimoterru.walls.domain.repository.WallpaperRepository
import it.kimoterru.walls.util.Resource
import javax.inject.Inject

class GetLatestPhotosUseCase @Inject constructor(
    private val repository: WallpaperRepository
) {

    suspend fun invoke(
        clientId: String,
        page: Int
    ): Resource<List<Photo>> {
        return try {
            val requestData = repository.getLatestPhotos(clientId, page)
            Resource.success(requestData)
        } catch (e: Exception) {
            Resource.error(msg = e.message.toString())
        }
    }
}