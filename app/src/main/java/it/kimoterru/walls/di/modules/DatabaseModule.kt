package it.kimoterru.walls.di.modules

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import it.kimoterru.walls.data.database.AppDatabase
import it.kimoterru.walls.data.database.PhotoDao
import it.kimoterru.walls.util.Constants
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {
    @Provides
    fun provideChannelDao(appDatabase: AppDatabase): PhotoDao {
        return appDatabase.photoDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext, AppDatabase::class.java, Constants.DATABASE_NAME
        ).build()
    }
}