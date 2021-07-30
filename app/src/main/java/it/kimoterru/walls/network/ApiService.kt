package it.kimoterru.walls.network

import it.kimoterru.walls.model.unsplash.PhotoItem
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    companion object {
        const val BASE_URL = "https://api.unsplash.com/"
    }

    @GET("/photos")
    suspend fun getLatest(@Query("client_id") clientId: String, @Query("page") page: Int): List<PhotoItem>
}