package it.kimoterru.walls.domain.models.search

import it.kimoterru.walls.domain.models.photo.Photo

data class SearchPhoto(
    val total: Int,
    val total_pages: Int,
    val resultsPhoto: List<Photo>
)