package it.kimoterru.walls.domain.usecase.detailI

import it.kimoterru.walls.domain.models.photo.Photo
import it.kimoterru.walls.domain.repository.WallpaperRepository
import it.kimoterru.walls.util.Resource
import javax.inject.Inject

class GetPhotoFromFavoriteByIDUseCase @Inject constructor(
    private val repository: WallpaperRepository
) {

    suspend fun invoke(
        id_photo: Int
    ): Resource<Photo> {
        return try {
            val requestData = repository.getPhotoFromFavoriteByID(id_photo)
            if (requestData != null) {
                Resource.success(requestData)
            } else {
                Resource.error(msg = "Don't data")
            }
        } catch (e: Exception) {
            Resource.error(msg = e.message.toString())
        }
    }
}