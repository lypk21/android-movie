package com.example.movie.api.model

import com.google.gson.annotations.SerializedName

data class MovieApiEntity(
    @SerializedName("id")
    val id: Int,
    @SerializedName("backdrop_path")
    val backdropPath: String? = "",
    @SerializedName("original_language")
    val originalLanguage: String? = "",
    @SerializedName("original_title")
    val originalTitle: String? = "",
    @SerializedName("overview")
    val overview: String? = "",
    @SerializedName("popularity")
    val popularity: Float? = 0F,
    @SerializedName("poster_path")
    val posterPath: String? = "",
    @SerializedName("release_date")
    val releaseDate: String? = "",
    @SerializedName("title")
    val title: String? = "",
    @SerializedName("video")
    val video: Boolean? = false,
    @SerializedName("vote_average")
    val voteAverage: String? = "",
    @SerializedName("vote_count")
    val voteCount: Int? = 0
    )
