package it.kimoterru.walls.data.local

import androidx.room.*
import it.kimoterru.walls.data.remote.models.photo.PhotoResponse

@Dao
interface WallpaperDao {
    @Query("SELECT * FROM photoItem")
    suspend fun getAllPhoto(): List<PhotoResponse>

    @Query("SELECT * FROM photoItem WHERE id_photo_is_local = :id")
    suspend fun getById(id: Int): PhotoResponse?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPhoto(photo: PhotoResponse)

    @Update
    suspend fun updatePhoto(photo: PhotoResponse?)

    @Delete
    suspend fun deletePhoto(photo: PhotoResponse?)
}