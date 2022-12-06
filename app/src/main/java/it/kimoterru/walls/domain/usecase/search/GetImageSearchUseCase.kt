package it.kimoterru.walls.domain.usecase.search

import it.kimoterru.walls.domain.models.search.SearchPhoto
import it.kimoterru.walls.domain.repository.WallpaperRepository
import javax.inject.Inject

class GetImageSearchUseCase @Inject constructor(
    private val repository: WallpaperRepository
) {

    suspend fun invoke(
        query: String,
        clientId: String,
        page: Int,
        per_page: Int,
        order_by: String,
    ): SearchPhoto {
        return repository.getImageSearch(query, clientId, page, per_page, order_by)
    }
}