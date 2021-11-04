package it.kimoterru.walls.adapter

interface WallpaperClickListener {
    interface WallpaperClick {
        fun onWallpaperClick(
            id: String,
            urlImageUser: String
        )
    }

    fun onColorClick(
        name: String,
    )

    fun onCategoryClick(
        name: String,
        tittle: String,
        totalPhotos: Int
    )
}