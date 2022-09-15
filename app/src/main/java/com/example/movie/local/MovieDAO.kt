package com.example.movie.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.movie.util.MOVIE_PAGINATION_PAGE_SIZE

@Dao
interface MovieDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movieRoomEntity: MovieRoomEntity): Long

    @Query("SELECT * FROM movies ORDER BY id ASC LIMIT :pageSize OFFSET :page")
    suspend fun getList( page: Int,
                         pageSize: Int? = MOVIE_PAGINATION_PAGE_SIZE): List<MovieRoomEntity>

    @Query("SELECT * FROM movies where movieId = :movieId")
    suspend fun getDetail(movieId: Int): MovieRoomEntity
}