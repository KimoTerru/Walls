package it.kimoterru.walls.network

import it.kimoterru.walls.models.photo.PhotoItem
import it.kimoterru.walls.models.categories.TopicItem
import it.kimoterru.walls.models.color.ColorItem
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    companion object {
        const val BASE_URL = "https://api.unsplash.com/"
    }

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
    ): List<PhotoItem>

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
        @Query("per_page") per_page: Int,
        @Query("order_by") order_by: String
    ): List<PhotoItem>

    @GET("/search/photos")
    suspend fun getColorImage(
        @Query("query") query: String,
        @Query("color") color: String,
        @Query("client_id") clientId: String,
        @Query("per_page") per_page: Int,
        @Query("order_by") order_by: String //@Body results: PhotoItem
    ): ColorItem
}