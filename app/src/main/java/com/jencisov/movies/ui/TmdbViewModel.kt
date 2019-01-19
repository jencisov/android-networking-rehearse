package com.jencisov.movies.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jencisov.movies.data.TmdbMovie
import com.jencisov.movies.services.ApiFactory
import com.jencisov.movies.services.MovieRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.cancel
import kotlin.coroutines.CoroutineContext

class TmdbViewModel : ViewModel() {

    private val parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Default
    private val scope = CoroutineScope(coroutineContext)
    private val repository = MovieRepository(ApiFactory.tmdbApi)
    private val _popularMoviesLiveData = MutableLiveData<List<TmdbMovie>>()
    val popularMoviesLiveData: LiveData<List<TmdbMovie>>
        get() = _popularMoviesLiveData

    fun fetchMovies() {
        scope.launch {
            val popularMovies = repository.getPopularMovies()
            _popularMoviesLiveData.postValue(popularMovies)
        }
    }

    fun cancelAllRequests() = coroutineContext.cancel()

}