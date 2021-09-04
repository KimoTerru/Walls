package it.kimoterru.walls.adapter

interface WallpaperClickListener {
    interface WallpaperClick {
        fun onWallpaperClick(
            id: String,
            urlImage: String,
            urlDownload: String,
            created: String,
            updated: String,
            userName: String,
            name: String,
        )
    }

    fun onColorClick(
        name: String,
    )

    fun onCategoryClick(
        name: String,
        tittle: String,
        totalPhotos: Int,
    )
}