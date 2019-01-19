package com.jencisov.movies.services

import android.util.Log
import com.jencisov.movies.data.TmdbMovie

class MovieRepository(private val api: TmdbApi) {

    suspend fun getPopularMovies(): MutableList<TmdbMovie> {
        val popularMovies: MutableList<TmdbMovie> = ArrayList()
        val popularMovieRequest = api.getPopularMovie()

        try {
            val response = popularMovieRequest.await()

            if (response.isSuccessful) {
                response.body()?.let { movieResponse ->
                    popularMovies.addAll(movieResponse.results)
                }
            } else {
                Log.d("FetchMovie", response.errorBody().toString())
            }
        } catch (e: Exception) {

        }

        return popularMovies
    }

}