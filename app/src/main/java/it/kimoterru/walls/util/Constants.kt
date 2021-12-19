package it.kimoterru.walls.util

import it.kimoterru.walls.R

class Constants {
    companion object {
        const val CLIENT_ID = "NPk5uWaTODGbpS35GoQyu7WulRGLX1jsYgn0mkVKhc4"
        const val DATABASE_NAME = "photo_db.db"
        const val PER_PAGE = 30
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