package com.example.movie.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie.local.MovieRoomEntity
import com.example.movie.repository.MovieRepository
import com.example.movie.util.Resource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class MovieViewModel
    @ViewModelInject
    constructor(private val movieRepository: MovieRepository): ViewModel() {
    private val _movies : MutableLiveData<Resource<List<MovieRoomEntity>>> = MutableLiveData()
    private val _movie : MutableLiveData<Resource<MovieRoomEntity>> = MutableLiveData()

    val movies : LiveData<Resource<List<MovieRoomEntity>>>
            get() = _movies

    val movie : LiveData<Resource<MovieRoomEntity>>
        get() = _movie

    fun getMovies(page: Int)  {
        viewModelScope.launch {
            movieRepository.getMovies(page).onEach {
                _movies.value = it
            }
            .launchIn(viewModelScope)
        }
    }

    fun getMovieById(movieId: Int) {
        viewModelScope.launch {
            movieRepository.getDetail(movieId).onEach {
                _movie.value = it
            }
                .launchIn(viewModelScope)
        }
    }
}