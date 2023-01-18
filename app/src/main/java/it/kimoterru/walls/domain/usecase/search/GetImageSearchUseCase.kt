package it.kimoterru.walls.domain.usecase.search

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import it.kimoterru.walls.domain.models.photo.Photo
import it.kimoterru.walls.domain.repository.WallpaperRepository
import javax.inject.Inject

class GetImageSearchUseCase @Inject constructor(
    private val repository: WallpaperRepository
) {

    suspend fun invoke(
        witchQuery: String,
        query: String,
        color: String,
        order_by: String,
    ): LiveData<PagingData<Photo>> {
        return repository.getImageSearch(witchQuery, query, color, order_by)
    }
}