package it.kimoterru.walls.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import it.kimoterru.walls.data.remote.models.photo.PhotoResponse

@Database(entities = [PhotoResponse::class], version = 1)
abstract class WallpaperDatabase : RoomDatabase() {
    abstract fun wallpaperDao(): WallpaperDao
}