package it.kimoterru.walls.data.remote.models.search

import com.google.gson.annotations.SerializedName
import it.kimoterru.walls.data.remote.models.photo.PhotoResponse
import it.kimoterru.walls.data.remote.models.photo.UserResponse

data class SearchResponse(
    @SerializedName("total")
    val total: Int,
    @SerializedName("total_pages")
    val total_pages: Int,
    @SerializedName("results")
    val resultsPhoto: List<PhotoResponse>,
    @SerializedName("results")
    val resultsUsers: List<UserResponse>
)