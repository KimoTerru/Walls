package it.kimoterru.walls.network

import it.kimoterru.walls.models.photo.PhotoItem
import it.kimoterru.walls.models.categories.TopicItem
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
    ): PhotoItem

    @GET("/topics")
    suspend fun getTopicsList(
        @Query("client_id") clientId: String,
        @Query("page") page: Int,
        @Query("per_page") per_page: Int,
        @Query("order_by") order_by: String
    ): List<TopicItem>
}