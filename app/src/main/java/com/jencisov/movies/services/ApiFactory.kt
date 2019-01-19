package com.jencisov.movies.services

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.jencisov.movies.Constants
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object ApiFactory {

    private val authInterceptor = Interceptor { chain ->
        val newUrl = chain.request()
                .url()
                .newBuilder()
                .addQueryParameter("api_key", Constants.TMDB_API_KEY)
                .build()

        val newRequest = chain.request()
                .newBuilder()
                .url(newUrl)
                .build()

        chain.proceed(newRequest)
    }

    private val tmdbClient = OkHttpClient().newBuilder()
            .addInterceptor(authInterceptor)
            .build()

    private val retrofit = Retrofit.Builder()
            .client(tmdbClient)
            .baseUrl(Constants.TMDB_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()

    val tmdbApi = retrofit.create(TmdbApi::class.java)

}