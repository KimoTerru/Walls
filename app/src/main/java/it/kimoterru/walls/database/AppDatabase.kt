package it.kimoterru.walls.database

import androidx.room.Database
import androidx.room.RoomDatabase
import it.kimoterru.walls.models.photo.PhotoItem

@Database(entities = [PhotoItem::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun photoDao(): PhotoDao
}