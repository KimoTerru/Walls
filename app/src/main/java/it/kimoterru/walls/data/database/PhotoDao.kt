package it.kimoterru.walls.data.database

import androidx.room.*
import it.kimoterru.walls.data.models.photo.PhotoItem

@Dao
interface PhotoDao {
    @Query("SELECT * FROM photoItem")
    suspend fun getAllPhoto(): List<PhotoItem>

    @Query("SELECT * FROM photoItem WHERE id_photo = :id")
    suspend fun getById(id: Int): PhotoItem?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPhoto(photo: PhotoItem)

    @Update
    suspend fun updatePhoto(photo: PhotoItem?)

    @Delete
    suspend fun deletePhoto(photo: PhotoItem?)
}