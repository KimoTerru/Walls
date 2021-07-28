package it.kimoterru.walls.repo

import it.kimoterru.walls.model.Wallpaper
import it.kimoterru.walls.network.ApiService
import javax.inject.Inject

class WallpaperRepository @Inject constructor(private var service: ApiService) {
    suspend fun getTopWallpapers(
        category: String?,
        purity: String?,
        topRange: String?,
        sorting: String?,
        order: String?,
        page: Int,
    ): Wallpaper {
        return service.listTopListWallpapers(category, purity, topRange, sorting, order, page)
    }

    suspend fun getLatestWallpapers(
        page: Int
    ): Wallpaper {
        return service.listLatestWallpapers(page)
    }
}