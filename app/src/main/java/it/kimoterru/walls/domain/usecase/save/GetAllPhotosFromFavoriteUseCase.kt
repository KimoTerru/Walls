package it.kimoterru.walls.domain.usecase.save

import it.kimoterru.walls.domain.models.photo.Photo
import it.kimoterru.walls.domain.repository.WallpaperRepository
import it.kimoterru.walls.util.Resource
import javax.inject.Inject

class GetAllPhotosFromFavoriteUseCase @Inject constructor(
    private val repository: WallpaperRepository
) {

    suspend fun invoke(): Resource<List<Photo>> {
        return try {
            val requestData = repository.getAllPhotosFromFavorite()
            if (requestData.isNotEmpty()) {
                Resource.success(requestData)
            } else {
                Resource.error(msg = "Don't data")
            }
        } catch (e: Exception) {
            Resource.error(msg = e.message.toString())
        }
    }
}