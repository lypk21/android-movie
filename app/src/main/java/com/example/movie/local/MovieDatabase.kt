package com.example.movie.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [MovieRoomEntity::class], version = 2)
abstract class MovieDatabase: RoomDatabase() {
    abstract fun movieDao(): MovieDAO

    companion object {
        val DATABASE_NAME: String = "movie_db"
    }


}