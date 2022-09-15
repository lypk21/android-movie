package com.example.movie.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movie.R
import com.example.movie.ui.MovieAdapter.Listener
import com.example.movie.util.MOVIE_DISPLAY_COLUMN
import com.example.movie.util.MOVIE_PAGINATION_PAGE_SIZE
import com.example.movie.util.PageInfo
import com.example.movie.util.Status
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_movie_list.*
import kotlinx.android.synthetic.main.common_loading.*
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MovieListActivity : AppCompatActivity() {
    private val TAG: String = "MovieListActivity"
    private lateinit var adapter: MovieAdapter
    private val pageInfo = PageInfo()

    private val viewModel:MovieViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)
        setupUI()
        subscribeObservers()
        viewModel.getMovies(pageInfo.page)
    }

    private fun subscribeObservers() {
        viewModel.movies.observe(this, Observer {
            when(it.status) {
                Status.LOADING -> {
                    progressBar.visibility = View.VISIBLE
                    recyclerView.visibility = View.GONE
                }

                Status.SUCCESS -> {
                    progressBar.visibility = View.GONE
                    recyclerView.visibility = View.VISIBLE
                    finishRefreshLoadMore()
                    it.data?.let { movies ->
                        if (pageInfo.isFirstPage) {
                            adapter.initData(movies)
                        } else {
                            adapter.addData(movies)
                            adapter.notifyDataSetChanged()
                        }

                        if(movies.size < MOVIE_PAGINATION_PAGE_SIZE) {
                            Log.d("getMoviespageSize3", "MOVIE_PAGINATION_PAGE_SIZE")
                            smartRefresh.finishLoadMoreWithNoMoreData()
                        }
                    }
                }

                Status.ERROR -> {
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    progressBar.visibility = View.GONE
                    recyclerView.visibility = View.VISIBLE
                    finishRefreshLoadMore()
                }
            }
        })
    }

    private fun setupUI() {
        recyclerView.layoutManager = GridLayoutManager(this, MOVIE_DISPLAY_COLUMN)
        adapter = MovieAdapter(arrayListOf())
        recyclerView.adapter = adapter

        smartRefresh.setOnRefreshLoadMoreListener(object : OnRefreshLoadMoreListener {
            override fun onLoadMore(refreshLayout: RefreshLayout) {
                pageInfo.nextPage()
                viewModel.getMovies(pageInfo.page)
            }

            override fun onRefresh(refreshLayout: RefreshLayout) {
                pageInfo.reset()
                viewModel.getMovies(pageInfo.page)
            }
        })

        adapter.setListener(object : Listener {
            override fun onItemClick(moveId: Int) {
                var intent = Intent(this@MovieListActivity, MovieDetailActivity::class.java)
                intent.putExtra("movieId", moveId)
                startActivity(intent)
            }
        })

    }

    private fun finishRefreshLoadMore() {
        Log.d("getMoviespageSize4", "finishRefreshLoadMore")
        smartRefresh.finishRefresh()
        smartRefresh.finishLoadMore()
    }
}