package it.kimoterru.walls.model

import com.google.gson.annotations.SerializedName

data class Meta(
    @SerializedName("current_page")
    val currentPage: Int,

    @SerializedName("last_page")
    val lastPage: Int,

    @SerializedName("per_page")
    val perPage: String,

    @SerializedName("total")
    val total: String,

    @SerializedName("query")
    val query: String
)
