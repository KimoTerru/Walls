package it.kimoterru.walls.data.local

import androidx.room.*
import it.kimoterru.walls.data.local.model.photo.PhotoEntity

@Dao
interface WallpaperDao {
    @Query("SELECT * FROM photoItem")
    suspend fun getAllPhoto(): List<PhotoEntity>

    @Query("SELECT * FROM photoItem WHERE id_photo_is_local = :id")
    suspend fun getById(id: Int): PhotoEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPhoto(photo: PhotoEntity)

    @Update
    suspend fun updatePhoto(photo: PhotoEntity?)

    @Delete
    suspend fun deletePhoto(photo: PhotoEntity?)
}