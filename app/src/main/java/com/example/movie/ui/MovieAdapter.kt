package com.example.movie.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movie.local.MovieRoomEntity
import com.example.movie.R
import com.example.movie.util.MOVIE_IMAGE_BASE_PATH
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieAdapter(private val movies: ArrayList<MovieRoomEntity>): RecyclerView.Adapter<MovieAdapter.DataViewHolder>() {


    class DataViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(movie: MovieRoomEntity) {
            Glide.with(itemView.rv_image.context)
                .load(MOVIE_IMAGE_BASE_PATH + movie.posterPath)
                .into(itemView.rv_image)

            itemView.tv_title.text = movie.title
        }

    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
       holder.bind(movies[position])
    }

    override fun getItemCount(): Int = movies.size

    fun initData(list:List<MovieRoomEntity>) {
        movies.clear()
        movies.addAll(list)
    }
    fun addData(list:List<MovieRoomEntity>) {
        movies.addAll(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        return DataViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        )
    }
}
