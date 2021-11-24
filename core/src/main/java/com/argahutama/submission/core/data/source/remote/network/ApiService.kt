package com.argahutama.submission.core.data.source.remote.network

import com.argahutama.submission.core.data.source.remote.response.MoviesResponse
import com.argahutama.submission.core.data.source.remote.response.TvShowsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("movie")
    suspend fun getMovies(@Query("api_key") apiKey: String): MoviesResponse

    @GET("tv")
    suspend fun getTvShows(@Query("api_key") apiKey: String): TvShowsResponse
}