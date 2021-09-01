package it.kimoterru.walls.adapter

interface WallpaperClickListener {
    fun onWallpaperClick(
        id: String,
        urlImage: String,
        urlDownload: String,
        created: String,
        updated: String,
        userName: String,
        name: String
    )
}