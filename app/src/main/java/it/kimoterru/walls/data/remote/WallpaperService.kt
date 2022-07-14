package it.kimoterru.walls.data.remote

import it.kimoterru.walls.data.remote.models.categories.TopicItem
import it.kimoterru.walls.data.remote.models.photo.PhotoItem
import it.kimoterru.walls.data.remote.models.search.SearchItem
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WallpaperService {

    @GET("/photos")
    suspend fun getLatest(
        @Query("client_id") clientId: String,
        @Query("page") page: Int,
    ): List<PhotoItem>

    @GET("/photos")
    suspend fun getTopList(
        @Query("client_id") clientId: String,
        @Query("page") page: Int,
        @Query("per_page") per_page: Int,
        @Query("order_by") order_by: String
    ): List<PhotoItem>

    @GET("/photos/{id}")
    suspend fun getPhoto(
        @Path("id") id: String,
        @Query("client_id") clientId: String
    ): PhotoItem

    @GET("/topics")
    suspend fun getTopicsList(
        @Query("client_id") clientId: String,
        @Query("page") page: Int,
        @Query("per_page") per_page: Int,
        @Query("order_by") order_by: String
    ): List<TopicItem>

    @GET("/topics/{id_or_slug}/photos")
    suspend fun getTopicImage(
        @Path("id_or_slug") id_or_slug: String,
        @Query("client_id") clientId: String,
        @Query("page") page: Int,
        @Query("per_page") per_page: Int,
        @Query("order_by") order_by: String
    ): List<PhotoItem>

    @GET("/search/photos")
    suspend fun getColorImage(
        @Query("query") query: String,
        @Query("color") color: String,
        @Query("client_id") clientId: String,
        @Query("page") page: Int,
        @Query("per_page") per_page: Int,
        @Query("order_by") order_by: String
    ): SearchItem

    @GET("/search/photos")
    suspend fun getSearchImage(
        @Query("query") query: String,
        @Query("client_id") clientId: String,
        @Query("page") page: Int,
        @Query("per_page") per_page: Int,
        @Query("order_by") order_by: String
    ): SearchItem
}