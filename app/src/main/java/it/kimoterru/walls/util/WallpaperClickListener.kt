package it.kimoterru.walls.util

interface WallpaperClickListener {
    interface WallpaperClick {
        fun onWallpaperClick(
            id: String, urlImageUser: String, idFavoritePhoto: Int
        )
    }

    interface HomeFragment {
        fun onColorClick(
            name: String,
        )

        fun onTopicClick(
            name: String,
            tittle: String,
            totalPhotos: Int
        )
    }
}