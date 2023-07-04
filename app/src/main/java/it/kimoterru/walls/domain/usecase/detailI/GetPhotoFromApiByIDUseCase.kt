package it.kimoterru.walls.domain.usecase.detailI

import it.kimoterru.walls.domain.models.photo.Photo
import it.kimoterru.walls.domain.repository.WallpaperRepository
import it.kimoterru.walls.util.Resource
import javax.inject.Inject

class GetPhotoFromApiByIDUseCase @Inject constructor(
    private val repository: WallpaperRepository
) {

    suspend fun invoke(
        id: String,
        clientId: String
    ): Resource<Photo> {
        return try {
            val requestData = repository.getPhotoFromApiByID(id, clientId)
            Resource.success(requestData)
        } catch (e: Exception) {
            Resource.error(msg = e.message.toString())
        }
    }
}