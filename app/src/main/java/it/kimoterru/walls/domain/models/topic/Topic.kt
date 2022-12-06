package it.kimoterru.walls.domain.models.topic

import it.kimoterru.walls.domain.models.photo.Photo
import it.kimoterru.walls.domain.models.photo.PhotoLinks
import it.kimoterru.walls.domain.models.photo.User

data class Topic(
    val id: String,
    val slug: String,
    val title: String,
    val description: String? = null,
    val startsAt: String? = null,
    val updatedAt: String? = null,
    val endsAt: String? = null,
    val featured: Boolean? = null,
    val totalPhotos: Int,
    val links: PhotoLinks? = null,
    val coverPhoto: Photo? = null,
    val previewPhotos: List<Photo>? = null,
    val status: String? = null,
    val owners: List<User>? = null,
    val topContributors: User? = null,
)