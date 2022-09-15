package com.example.movie.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movies")
class MovieRoomEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo(name = "movieId")
    val movieId: Int,
    @ColumnInfo(name = "backdropPath")
    val backdropPath: String? = "",
    @ColumnInfo(name = "originalLanguage")
    val originalLanguage: String? = "",
    @ColumnInfo(name = "originalTitle")
    val originalTitle: String? = "",
    @ColumnInfo(name = "overview")
    val overview: String? = "",
    @ColumnInfo(name = "popularity")
    val popularity: Float? = 0F,
    @ColumnInfo(name = "posterPath")
    val posterPath: String? = "",
    @ColumnInfo(name = "releaseDate")
    val releaseDate: String? = "",
    @ColumnInfo(name = "title")
    val title: String? = "",
    @SerializedName("video")
    val video: Boolean? = false,
    @SerializedName("vote_average")
    val voteAverage: String? = "",
    @SerializedName("vote_count")
    val voteCount: Int? = 0
    )
