package com.jencisov.movies.services

import com.jencisov.movies.data.TmdbMovie
import com.jencisov.movies.data.TmdbMovieResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface TmdbApi {

    @GET("movie/popular")
    fun getPopularMovie(): Deferred<Response<TmdbMovieResponse>>

    @GET("movie/{id}")
    fun getMovieById(@Path("id") id: Int): Deferred<Response<TmdbMovie>>

}