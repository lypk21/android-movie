package com.example.movie.di

import com.example.movie.api.MovieRetrofit
import com.example.movie.util.MOVIE_API_KEY
import com.example.movie.util.MOVIE_BASE_URL
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RetrofitModule {
    @Singleton
    @Provides
    fun provideGsonBuilder() : Gson {
        return GsonBuilder().create()
    }

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson): Retrofit.Builder {
        return Retrofit.Builder().baseUrl(MOVIE_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
    }

    @Singleton
    @Provides
    fun provideMovieService(retrofit: Retrofit.Builder): MovieRetrofit {
        return retrofit.build().create(MovieRetrofit::class.java)
    }

    @Singleton
    @Provides
    @Named("auth_token")
    fun provideAuthToken(): String{
        return MOVIE_API_KEY
    }
}