package it.kimoterru.walls.domain.usecase.search

import it.kimoterru.walls.domain.models.photo.Photo
import it.kimoterru.walls.domain.repository.WallpaperRepository
import javax.inject.Inject

class GetImageTopicsUseCase @Inject constructor(
    private val repository: WallpaperRepository
) {

    suspend fun invoke(
        id_or_slug: String,
        clientId: String,
        page: Int,
        per_page: Int,
        order_by: String
    ): List<Photo> {
        return repository.getImageTopics(id_or_slug, clientId, page, per_page, order_by)
    }
}