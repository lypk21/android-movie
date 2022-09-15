package com.example.movie.api

import com.example.movie.api.model.MovieApiEntity
import com.example.movie.api.response.MoviesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieRetrofit {
    @GET("movie/popular")
    suspend fun getList(@Query("api_key") token: String, @Query("page") page: Int): MoviesResponse

    @GET("movie/{id}")
    suspend fun getDetail(@Path("id") movieId:Int, @Query("api_key") token: String): MovieApiEntity

    @GET("search/movie")
    suspend fun searchList(@Query("api_key") token: String, @Query("query") query: String, @Query("page") page: Int): MoviesResponse
}