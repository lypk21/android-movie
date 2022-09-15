package com.example.movie.repository

import android.util.Log
import com.example.movie.api.MovieRetrofit
import com.example.movie.local.MovieDAO
import com.example.movie.local.MovieRoomEntity
import com.example.movie.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MovieRepository(
    private val movieRetrofit: MovieRetrofit,
    private val movieDAO: MovieDAO,
    private val token: String
    ) {

   suspend fun getMovies(page: Int): Flow<Resource<List<MovieRoomEntity>>> = flow {
       emit(Resource.loading(null))
       try {
           val moviesApi = movieRetrofit.getList(token, page)
           for (movie in moviesApi.results) {
               movieDAO.insert(
                   MovieRoomEntity(
                       movieId = movie.id,
                       backdropPath = movie.backdropPath,
                       originalLanguage = movie.originalLanguage,
                       originalTitle = movie.originalTitle,
                       overview = movie.overview,
                       popularity = movie.popularity,
                       posterPath = movie.posterPath,
                       releaseDate = movie.releaseDate,
                       title = movie.title,
                       video = movie.video,
                       voteAverage = movie.voteAverage,
                       voteCount = movie.voteCount
               ))
           }
           val movies = movieDAO.getList(page);
           emit(Resource.success(movies))
       } catch (e: Exception) {
           emit(Resource.error(e.message.toString(), null))
           emit(Resource.success(movieDAO.getList(page)))
       }
   }

}