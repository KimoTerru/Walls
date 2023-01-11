package it.kimoterru.walls.util

interface WallpaperClickListener {
    interface WallpaperClick {
        fun onWallpaperClick(
            idNetworkPhoto: String, idLocalPhoto: Int, urlImageUser: String
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