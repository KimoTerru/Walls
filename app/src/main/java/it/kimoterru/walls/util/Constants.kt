package it.kimoterru.walls.util

class Constants {
    companion object {
        const val CLIENT_ID = "2Jlbb8ZW8_QzYzbwo8BbrVKCWE3QMel2kDWrU5z1oq0" //Access Key WallpaperApplication
        const val BASE_URL = "https://api.unsplash.com/"
        const val DATABASE_NAME = "photo_db.db"
        const val PER_PAGE = 30 //Only 50 requests per hour 30 x 50 = 1500
        const val FIRST_PAGE = 1
        const val CROSS_FADE_DURATION = 350

        const val topics = "topics"
        const val colors = "colors"
        const val search = "search"
        const val zero = 0
        const val latest = "latest"
        const val latestWallpapers = "Latest wallpapers"
        const val jpg = ".jpg"
    }
}

enum class TopicsOrder(var query: String) {
    FEATURED("featured"),
    LATEST("latest"),
    OLDEST("oldest"),
    POSITION("position") //default
}