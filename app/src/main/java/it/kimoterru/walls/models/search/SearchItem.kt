package it.kimoterru.walls.models.search

import com.google.gson.annotations.SerializedName
import it.kimoterru.walls.models.photo.PhotoItem

data class SearchItem(
    @SerializedName("total")
    val total: Int,
    @SerializedName("total_pages")
    val total_pages: Int,
    @SerializedName("results")
    val results: List<PhotoItem>
)