package it.kimoterru.walls.util

interface WallpaperClickListener {
    interface WallpaperClick {
        fun onWallpaperClick(
            idNetworkPhoto: String, idLocalPhoto: Int, urlImageUser: String
        )
    }

    interface LongClick{
        fun onLongClick(
            fileName: String, linkDownload: String,
        )
    }

    interface HomeFragment {
        fun onLatestPhoto()

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