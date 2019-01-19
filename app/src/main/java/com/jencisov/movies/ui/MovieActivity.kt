package com.jencisov.movies.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.jencisov.movies.R
import com.jencisov.movies.data.TmdbMovie
import kotlinx.android.synthetic.main.activity_movie.list

class MovieActivity : AppCompatActivity() {

    private val tmdbViewModel: TmdbViewModel by lazy { ViewModelProviders.of(this).get(TmdbViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)

        tmdbViewModel.fetchMovies()
        tmdbViewModel.popularMoviesLiveData.observe(::getLifecycle, ::fillList)
    }

    private fun fillList(moviesList: List<TmdbMovie>) {
        list.adapter = MovieAdapter(moviesList)
    }

}