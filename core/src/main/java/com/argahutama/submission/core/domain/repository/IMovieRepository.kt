package com.argahutama.submission.core.domain.repository

import com.argahutama.submission.core.data.Resource
import com.argahutama.submission.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {
    fun getAllMovies(sort: String): Flow<Resource<List<Movie>>>

    fun getAllTvShows(sort: String): Flow<Resource<List<Movie>>>

    fun getFavoriteMovies(sort: String): Flow<List<Movie>>

    fun getFavoriteTvShows(sort: String): Flow<List<Movie>>

    fun getSearchMovies(search: String): Flow<List<Movie>>

    fun getSearchTvShows(search: String): Flow<List<Movie>>

    fun setMovieFavorite(movie: Movie, state: Boolean)
}