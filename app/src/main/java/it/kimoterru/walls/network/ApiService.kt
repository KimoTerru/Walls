package it.kimoterru.walls.network

import it.kimoterru.walls.model.unsplash.PhotoItem
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/photos")
    suspend fun getLatest(@Query("client_id") clientId: String, @Query("page") page: Int): List<PhotoItem>
}