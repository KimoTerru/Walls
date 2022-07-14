package it.kimoterru.walls.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import it.kimoterru.walls.data.repository.WallpaperRepositoryImpl
import it.kimoterru.walls.domain.repository.WallpaperRepository

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindWallpaperRepository(
        repository: WallpaperRepositoryImpl
    ): WallpaperRepository
}