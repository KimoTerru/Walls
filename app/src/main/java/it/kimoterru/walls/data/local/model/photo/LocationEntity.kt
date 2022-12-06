package it.kimoterru.walls.data.local.model.photo

import androidx.room.ColumnInfo

data class LocationEntity(
    @ColumnInfo(name = "city_location")
    val city: String? = null,

    @ColumnInfo(name = "country_location")
    val country: String? = null
)