package it.kimoterru.walls.data.pagingsources

import androidx.paging.PagingSource
import androidx.paging.PagingState
import it.kimoterru.walls.data.mapper.toPhoto
import it.kimoterru.walls.data.mapper.toSearchPhoto
import it.kimoterru.walls.data.remote.WallpaperService
import it.kimoterru.walls.domain.models.photo.Photo
import it.kimoterru.walls.util.Constants.Companion.CLIENT_ID
import it.kimoterru.walls.util.Constants.Companion.PER_PAGE
import it.kimoterru.walls.util.Constants.Companion.colors
import it.kimoterru.walls.util.Constants.Companion.latest
import it.kimoterru.walls.util.Constants.Companion.search
import it.kimoterru.walls.util.Constants.Companion.topics
import retrofit2.HttpException
import java.io.IOException

class PhotoPagingSource(
    private val wallpaperService: WallpaperService,
    private val witchQuery: String,
    private val query: String,
    private val color: String,
    private val order_by: String
) : PagingSource<Int, Photo>() {

    override fun getRefreshKey(state: PagingState<Int, Photo>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val  anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Photo> {
        return try {
            val pageNumber = params.key ?: 1
            val response: List<Photo> = when(witchQuery) {
                search -> wallpaperService.getSearchImage(query, CLIENT_ID, pageNumber, PER_PAGE, order_by).toSearchPhoto().resultsPhoto
                colors -> wallpaperService.getColorImage(query, color, CLIENT_ID, pageNumber, PER_PAGE, order_by).toSearchPhoto().resultsPhoto
                topics -> wallpaperService.getTopicImage(query, CLIENT_ID, pageNumber, PER_PAGE, order_by).map { it.toPhoto() }
                latest -> wallpaperService.getLatest(CLIENT_ID, pageNumber, PER_PAGE, order_by).map { it.toPhoto() }
                else -> emptyList()
            }
            LoadResult.Page(
                data = response,
                prevKey = null,
                nextKey = pageNumber.plus(1)
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }
}