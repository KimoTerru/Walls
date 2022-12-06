package it.kimoterru.walls.data.mapper

import it.kimoterru.walls.data.local.model.photo.*
import it.kimoterru.walls.data.remote.models.photo.*
import it.kimoterru.walls.data.remote.models.search.SearchPhotoResponse
import it.kimoterru.walls.data.remote.models.topic.TopicResponse
import it.kimoterru.walls.domain.models.photo.*
import it.kimoterru.walls.domain.models.search.SearchPhoto
import it.kimoterru.walls.domain.models.topic.Topic

// Network model
fun PhotoResponse.toPhoto(): Photo {
    return Photo(
        id = id,
        id_photo_is_local = id_photo_is_local,
        createdAt = createdAt,
        updatedAt = updatedAt,
        width = width,
        height = height,
        color = color,
        downloads = downloads,
        blurHash = blurHash,
        likes = likes,
        likedByUser = likedByUser,
        publicDomain = publicDomain,
        description = description,
        altDescription = altDescription,
        exif = exif?.toExif(),
        location = location?.toLocation(),
        urls = urls?.toPhotoUrls(),
        links = links?.toPhotoLinks(),
        user = user?.toUser()
    )
}

private fun ExifResponse.toExif(): Exif {
    return Exif(
        make = make,
        model = model,
        name = name,
        exposure_time = exposure_time,
        aperture = aperture,
        focal_length = focal_length,
        iso = iso
    )
}

private fun LocationResponse.toLocation(): Location {
    return Location(
        city = city,
        country = country
    )
}

private fun PhotoUrlsResponse.toPhotoUrls(): PhotoUrls {
    return PhotoUrls(
        raw = raw,
        full = full,
        regular = regular,
        small = small,
        thumb = thumb
    )
}

private fun PhotoLinksResponse.toPhotoLinks(): PhotoLinks {
    return PhotoLinks(
        self = self,
        html = html,
        download = download,
        downloadLocation = downloadLocation
    )
}

private fun UserResponse.toUser(): User {
    return User(
        id = id,
        updatedAt = updatedAt,
        username = username,
        name = name,
        firstName = firstName,
        lastName = lastName,
        profileImage = profileImage?.toProfileImage(),
        portfolioUrl = portfolioUrl,
        bio = bio,
        location = location,
        totalLikes = totalLikes,
        totalPhotos = totalPhotos,
        acceptedTos = acceptedTos,
        totalCollections = totalCollections,
        instagramUsername = instagramUsername,
        twitterUsername = twitterUsername,
        links = links?.toUserLinks()
    )
}

private fun ProfileImageResponse.toProfileImage(): ProfileImage {
    return ProfileImage(
        large = large,
        medium = medium,
        small = small
    )
}

private fun UserLinksResponse.toUserLinks(): UserLinks {
    return UserLinks(
        self = self,
        html = html,
        photos = photos,
        likes = likes,
        portfolio = portfolio,
        following = following,
        followers = followers
    )
}

fun TopicResponse.toTopic(): Topic {
    return Topic(
        id = id,
        slug = slug,
        title = title,
        description = description,
        startsAt = startsAt,
        updatedAt = updatedAt,
        endsAt = endsAt,
        featured = featured,
        totalPhotos = totalPhotos,
        links = links?.toPhotoLinks(),
        coverPhoto = coverPhoto?.toPhoto(),
        previewPhotos = previewPhotos?.map { it.toPhoto() },
        status = status,
        owners = owners?.map { it.toUser() },
        topContributors = topContributors?.toUser()
    )
}

fun SearchPhotoResponse.toSearchPhoto(): SearchPhoto {
    return SearchPhoto(
        total = total,
        total_pages = total_pages,
        resultsPhoto = resultsPhoto.map { it.toPhoto() }
    )
}

// local model

fun PhotoEntity.toPhoto(): Photo {
    return Photo(
        id = id,
        id_photo_is_local = id_photo_is_local,
        createdAt = createdAt,
        updatedAt = updatedAt,
        width = width,
        height = height,
        color = color,
        downloads = downloads,
        blurHash = blurHash,
        likes = likes,
        likedByUser = likedByUser,
        publicDomain = publicDomain,
        description = description,
        altDescription = altDescription,
        exif = exif?.toExif(),
        location = location?.toLocation(),
        urls = urls?.toPhotoUrls(),
        links = links?.toPhotoLinks(),
        user = user?.toUser()
    )
}

private fun ExifEntity.toExif(): Exif {
    return Exif(
        make = make,
        model = model,
        name = name,
        exposure_time = exposure_time,
        aperture = aperture,
        focal_length = focal_length,
        iso = iso
    )
}

private fun LocationEntity.toLocation(): Location {
    return Location(
        city = city,
        country = country
    )
}

private fun PhotoUrlsEntity.toPhotoUrls(): PhotoUrls {
    return PhotoUrls(
        raw = raw,
        full = full,
        regular = regular,
        small = small,
        thumb = thumb
    )
}

private fun PhotoLinksEntity.toPhotoLinks(): PhotoLinks {
    return PhotoLinks(
        self = self,
        html = html,
        download = download,
        downloadLocation = downloadLocation
    )
}

private fun UserEntity.toUser(): User {
    return User(
        id = id,
        updatedAt = updatedAt,
        username = username,
        name = name,
        firstName = firstName,
        lastName = lastName,
        profileImage = profileImage?.toProfileImage(),
        portfolioUrl = portfolioUrl,
        bio = bio,
        location = location,
        totalLikes = totalLikes,
        totalPhotos = totalPhotos,
        acceptedTos = acceptedTos,
        totalCollections = totalCollections,
        instagramUsername = instagramUsername,
        twitterUsername = twitterUsername,
        links = links?.toUserLinks()
    )
}

private fun ProfileImageEntity.toProfileImage(): ProfileImage {
    return ProfileImage(
        large = large,
        medium = medium,
        small = small
    )
}

private fun UserLinksEntity.toUserLinks(): UserLinks {
    return UserLinks(
        self = self,
        html = html,
        photos = photos,
        likes = likes,
        portfolio = portfolio,
        following = following,
        followers = followers
    )
}

// domain model

fun Photo.toPhotoEntity(): PhotoEntity {
    return PhotoEntity(
        id = id,
        id_photo_is_local = id_photo_is_local,
        createdAt = createdAt,
        updatedAt = updatedAt,
        width = width,
        height = height,
        color = color,
        downloads = downloads,
        blurHash = blurHash,
        likes = likes,
        likedByUser = likedByUser,
        publicDomain = publicDomain,
        description = description,
        altDescription = altDescription,
        exif = exif?.toExifEntity(),
        location = location?.toLocationEntity(),
        urls = urls?.toPhotoUrlsEntity(),
        links = links?.toPhotoLinksEntity(),
        user = user?.toUserEntity()
    )
}

private fun Exif.toExifEntity(): ExifEntity {
    return ExifEntity(
        make = make,
        model = model,
        name = name,
        exposure_time = exposure_time,
        aperture = aperture,
        focal_length = focal_length,
        iso = iso
    )
}

private fun Location.toLocationEntity(): LocationEntity {
    return LocationEntity(
        city = city,
        country = country
    )
}

private fun PhotoUrls.toPhotoUrlsEntity(): PhotoUrlsEntity {
    return PhotoUrlsEntity(
        raw = raw,
        full = full,
        regular = regular,
        small = small,
        thumb = thumb
    )
}

private fun PhotoLinks.toPhotoLinksEntity(): PhotoLinksEntity {
    return PhotoLinksEntity(
        self = self,
        html = html,
        download = download,
        downloadLocation = downloadLocation
    )
}

private fun User.toUserEntity(): UserEntity {
    return UserEntity(
        id = id,
        updatedAt = updatedAt,
        username = username,
        name = name,
        firstName = firstName,
        lastName = lastName,
        profileImage = profileImage?.toProfileImageEntity(),
        portfolioUrl = portfolioUrl,
        bio = bio,
        location = location,
        totalLikes = totalLikes,
        totalPhotos = totalPhotos,
        acceptedTos = acceptedTos,
        totalCollections = totalCollections,
        instagramUsername = instagramUsername,
        twitterUsername = twitterUsername,
        links = links?.toUserLinksEntity()
    )
}

private fun ProfileImage.toProfileImageEntity(): ProfileImageEntity {
    return ProfileImageEntity(
        large = large,
        medium = medium,
        small = small
    )
}

private fun UserLinks.toUserLinksEntity(): UserLinksEntity {
    return UserLinksEntity(
        self = self,
        html = html,
        photos = photos,
        likes = likes,
        portfolio = portfolio,
        following = following,
        followers = followers
    )
}