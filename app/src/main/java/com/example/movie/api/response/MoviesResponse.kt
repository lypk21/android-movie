package com.example.movie.api.response

import com.example.movie.api.model.MovieApiEntity
import com.google.gson.annotations.SerializedName

data class MoviesResponse(
    @SerializedName("page")
    val page: Int ? = 1,
    @SerializedName("results")
    val results: List<MovieApiEntity>
)
