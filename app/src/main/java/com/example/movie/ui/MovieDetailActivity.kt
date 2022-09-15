package com.example.movie.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.movie.R
import com.example.movie.util.MOVIE_IMAGE_BASE_PATH
import com.example.movie.util.Status
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_movie_detail.*
import kotlinx.android.synthetic.main.common_loading.*
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MovieDetailActivity : AppCompatActivity() {
    private val TAG: String = "MovieDetailActivity"

    private val viewModel:MovieViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        subscribeObservers()

        viewModel.getMovieById(intent.extras?.get("movieId") as Int)
    }

    private fun subscribeObservers() {
        viewModel.movie.observe(this, Observer {
            when(it.status) {
                Status.LOADING -> {
                    progressBar.visibility = View.VISIBLE
                }

                Status.SUCCESS -> {
                    progressBar.visibility = View.GONE
                    it.data?.let { movie ->
                        Glide.with(posterPath.context)
                            .load(MOVIE_IMAGE_BASE_PATH + movie.posterPath)
                            .into(posterPath)
                        movieTitle.text = movie.title
                        moviePopular.text = String.format("Popularity: %s", movie.popularity.toString())
                        movieLanguage.text = String.format("Lang: %s", movie.originalLanguage)
                        movieReleaseDate.text = String.format("Release: %s", movie.releaseDate)
                        movieRate.text = String.format("Rate: %s", movie.voteAverage)
                        movieRateCount.text = String.format("VoteNum: %s", movie.voteCount.toString())
                        movieOverview.text = movie.overview
                    }
                }

                Status.ERROR -> {
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    progressBar.visibility = View.GONE
                }
            }
        })
    }

}