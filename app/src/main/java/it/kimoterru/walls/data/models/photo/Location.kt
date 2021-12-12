package it.kimoterru.walls.data.models.photo

import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName

data class Location(
    @ColumnInfo(name = "city_location")
    @SerializedName("city")
    val city: String? = null,

    @ColumnInfo(name = "country_location")
    @SerializedName("country")
    val country: String? = null
)