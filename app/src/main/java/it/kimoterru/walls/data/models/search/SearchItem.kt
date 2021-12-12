package it.kimoterru.walls.data.models.search

import com.google.gson.annotations.SerializedName
import it.kimoterru.walls.data.models.photo.PhotoItem

data class SearchItem(
    @SerializedName("total")
    val total: Int,
    @SerializedName("total_pages")
    val total_pages: Int,
    @SerializedName("results")
    val results: List<PhotoItem>
)