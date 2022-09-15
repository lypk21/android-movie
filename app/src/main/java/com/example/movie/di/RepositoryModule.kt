package com.example.movie.di

import com.example.movie.api.MovieRetrofit
import com.example.movie.local.MovieDAO
import com.example.movie.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun provideMovieRepository(
         movieRetrofit: MovieRetrofit,
         movieDAO: MovieDAO,
         @Named("auth_token")  token: String
    ): MovieRepository {
        return MovieRepository(movieRetrofit, movieDAO, token)
    }
}