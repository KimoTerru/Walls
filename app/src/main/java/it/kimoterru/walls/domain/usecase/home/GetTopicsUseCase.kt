package it.kimoterru.walls.domain.usecase.home

import it.kimoterru.walls.domain.models.topic.Topic
import it.kimoterru.walls.domain.repository.WallpaperRepository
import it.kimoterru.walls.util.Resource
import javax.inject.Inject

class GetTopicsUseCase @Inject constructor(
    private val repository: WallpaperRepository
) {

    suspend fun invoke(
        clientId: String,
        page: Int,
        per_page: Int,
        order_by: String,
    ): Resource<List<Topic>> {
        return try {
            val requestData = repository.getTopics(clientId, page, per_page, order_by)
            Resource.success(requestData)
        } catch (e: Exception) {
            Resource.error(msg = e.message.toString())
        }
    }
}