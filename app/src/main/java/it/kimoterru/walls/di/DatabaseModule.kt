package it.kimoterru.walls.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import it.kimoterru.walls.data.local.WallpaperDao
import it.kimoterru.walls.data.local.WallpaperDatabase
import it.kimoterru.walls.util.Constants
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {
    @Provides
    fun provideChannelDao(wallpaperDatabase: WallpaperDatabase): WallpaperDao {
        return wallpaperDatabase.photoDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): WallpaperDatabase {
        return Room.databaseBuilder(
            appContext, WallpaperDatabase::class.java, Constants.DATABASE_NAME
        ).build()
    }
}