package it.kimoterru.walls.network

import it.kimoterru.walls.model.Wallpaper
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    companion object {
        const val BASE_URL = "https://wallhaven.cc/api/v1/"
        const val GENERAL_CATEGORY = "com.example.wallhavenapp.generalcategory"
        const val ANIME_CATEGORY = "com.example.wallhavenapp.animecategory"
        const val PEOPLE_CATEGORY = "com.example.wallhavenapp.peoplecategory"
        const val SFW_PURITY = "com.example.wallhavenapp.sfwpurity"
        const val SKETCHY_PURITY = "com.example.wallhavenapp.sketchypurity"
        const val NSFW_PURITY = "com.example.wallhavenapp.nsfwpurity"
    }

    @GET("search")
    suspend fun listLatestWallpapers(@Query("page") page: Int): Wallpaper

    @GET("search")
    suspend fun listLatestWallpapers(
        @Query("categories") category: String?,
        @Query("purity") purity: String?,
        @Query("sorting") sorting: String?,
        @Query("order") order: String?,
        @Query("page") page: Int,
    ): Wallpaper

    @GET("search")
    suspend fun listTopListWallpapers(
        @Query("categories") category: String?,
        @Query("purity") purity: String?,
        @Query("topRange") topRange: String?,
        @Query("sorting") sorting: String?,
        @Query("order") order: String?,
        @Query("page") page: Int,
    ): Wallpaper

    @GET("search")
    suspend fun listSearchWallpapers(
        @Query("q") query: String?,
        @Query("page") page: Int,
    ): Wallpaper

    @GET("search")
    suspend fun listSearchWallpapers(
        @Query("q") query: String?,
        @Query("categories") category: String?,
        @Query("purity") purity: String?,
        @Query("sorting") sorting: String?,
        @Query("order") order: String?,
        @Query("page") page: Int,
    ): Wallpaper

    @GET("w/{wallpaperID}")
    suspend fun getWallpaper(@Path("wallpaperID") wallpaperID: String?): Wallpaper
}