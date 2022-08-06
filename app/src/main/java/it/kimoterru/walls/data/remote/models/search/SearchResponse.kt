package it.kimoterru.walls.data.remote.models.search

import com.google.gson.annotations.SerializedName
import it.kimoterru.walls.data.remote.models.photo.PhotoResponse

data class SearchResponse(
    @SerializedName("total")
    val total: Int,
    @SerializedName("total_pages")
    val total_pages: Int,
    @SerializedName("results")
    val results: List<PhotoResponse>
)