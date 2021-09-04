package it.kimoterru.walls.models.color

import com.google.gson.annotations.SerializedName
import it.kimoterru.walls.models.photo.PhotoItem

data class ColorItem(
    @SerializedName("total")
    val total: Int,
    @SerializedName("total_pages")
    val total_pages: Int,
    @SerializedName("results")
    val results: List<PhotoItem>
)