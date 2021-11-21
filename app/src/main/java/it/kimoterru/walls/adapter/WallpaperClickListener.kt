package it.kimoterru.walls.adapter

interface WallpaperClickListener {
    interface WallpaperClick {
        fun onWallpaperClick(
            id: String,
            urlImageUser: String
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