package it.kimoterru.walls.util

import it.kimoterru.walls.R

class Constants {
    companion object {
        const val CLIENT_ID =
            "-x8-cfEo3m34xVKTZtPWrIuTN5YIbv6VRp_TcBtXEkA" //Access Key WallpaperApplication
        const val BASE_URL = "https://api.unsplash.com/"
        const val DATABASE_NAME = "photo_db.db"
        const val PER_PAGE = 30 //Only 50 requests per hour 30 x 50 = 1500
        const val SHOW_NEXT_PAGE_IMAGE_TIME_DELAY = 750L
        const val FIRST_PAGE = 1
        const val CROSS_FADE_DURATION = 350

        const val topics = "topics"
        const val colors = "colors"
        const val search = "search"
        const val saved = "saved"
        const val notSaved = "notSaved"
        const val zero = 0
    }
}

enum class TopicsOrder(var query: String) {
    FEATURED("featured"),
    LATEST("latest"),
    OLDEST("oldest"),
    POSITION("position") //default
}

enum class WallpaperColors(var query: String, var colorRes: Int) {
    BLACKWHITE("black_and_white", -1),
    BLACK("black", R.color.wp_black),
    WHITE("white", R.color.wp_white),
    YELLOW("yellow", R.color.wp_yellow),
    ORANGE("orange", R.color.wp_orange),
    RED("red", R.color.wp_red),
    PURPLE("purple", R.color.wp_purple),
    MAGENTA("magenta", R.color.wp_magenta),
    GREEN("green", R.color.wp_green),
    TEAL("teal", R.color.wp_teal),
    BLUE("blue", R.color.wp_blue)
}