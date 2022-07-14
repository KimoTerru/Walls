package it.kimoterru.walls.domain.usecase.home

import it.kimoterru.walls.data.remote.models.categories.TopicItem
import it.kimoterru.walls.domain.repository.WallpaperRepository
import javax.inject.Inject

class GetTopicsUseCase @Inject constructor(
    private val repository: WallpaperRepository
) {

    suspend fun invoke(
        clientId: String,
        page: Int,
        per_page: Int,
        order_by: String,
    ): List<TopicItem> {
        return repository.getTopics(clientId, page, per_page, order_by)
    }
}