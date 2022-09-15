package com.example.movie.di

import android.content.Context
import androidx.room.Room
import com.example.movie.local.MovieDAO
import com.example.movie.local.MovieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RoomModule {
    @Singleton
    @Provides
    fun provideMovieDb(@ApplicationContext context: Context): MovieDatabase {
        return Room.databaseBuilder(
                context,
                MovieDatabase::class.java,
                MovieDatabase.DATABASE_NAME
            )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideMovieDAO(movieDatabase: MovieDatabase): MovieDAO {
        return movieDatabase.movieDao()
    }


}