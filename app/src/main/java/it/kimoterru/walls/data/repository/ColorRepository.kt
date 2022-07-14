package it.kimoterru.walls.data.repository

import it.kimoterru.walls.R
import it.kimoterru.walls.data.remote.models.color.Colors

private val colorRepository = listOf(
    Colors("black", R.color.wp_black),
    Colors("white", R.color.alice_blue),
    Colors("yellow", R.color.wp_yellow),
    Colors("orange", R.color.wp_orange),
    Colors("red", R.color.wp_red),
    Colors("purple", R.color.wp_purple),
    Colors("magenta", R.color.wp_magenta),
    Colors("green", R.color.wp_green),
    Colors("teal", R.color.wp_teal),
    Colors("blue", R.color.wp_blue)
)

fun getColors(): List<Colors> {
    return colorRepository
}