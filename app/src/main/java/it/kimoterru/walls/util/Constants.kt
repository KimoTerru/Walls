package it.kimoterru.walls.util

class Constants {
    companion object {
        const val CLIENT_ID = "NPk5uWaTODGbpS35GoQyu7WulRGLX1jsYgn0mkVKhc4"
    }
}

enum class TopicsOrder(var query: String) {
    FEATURED("featured"),
    LATEST("latest"),
    OLDEST("oldest"),
    POSITION("position") //default
}