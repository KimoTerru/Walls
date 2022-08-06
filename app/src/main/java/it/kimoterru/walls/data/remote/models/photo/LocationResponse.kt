package it.kimoterru.walls.data.remote.models.photo

import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName

data class LocationResponse(
    @ColumnInfo(name = "city_location")
    @SerializedName("city")
    val city: String? = null,

    @ColumnInfo(name = "country_location")
    @SerializedName("country")
    val country: String? = null
)