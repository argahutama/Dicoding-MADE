package com.argahutama.submission.core.data.source.local

import com.argahutama.submission.core.data.source.local.entity.MovieEntity
import com.argahutama.submission.core.data.source.local.room.MovieDao
import com.argahutama.submission.core.util.SortUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn

class LocalDataSource(private val mMovieDao: MovieDao) {

    fun getAllMovies(sort: String): Flow<List<MovieEntity>> {
        val query = SortUtil.getSortedQueryMovies(sort)
        return mMovieDao.getMovies(query)
    }

    fun getAllTvShows(sort: String): Flow<List<MovieEntity>> {
        val query = SortUtil.getSortedQueryTvShows(sort)
        return mMovieDao.getTvShows(query)
    }

    fun getAllFavoriteMovies(sort: String): Flow<List<MovieEntity>> {
        val query = SortUtil.getSortedQueryFavoriteMovies(sort)
        return mMovieDao.getFavoriteMovies(query)
    }

    fun getAllFavoriteTvShows(sort: String): Flow<List<MovieEntity>> {
        val query = SortUtil.getSortedQueryFavoriteTvShows(sort)
        return mMovieDao.getFavoriteTvShows(query)
    }

    fun searchMovie(search: String): Flow<List<MovieEntity>> = mMovieDao.searchMovies(search)
        .flowOn(Dispatchers.Default)
        .conflate()

    fun searchTvShow(search: String): Flow<List<MovieEntity>> = mMovieDao.searchTvShows(search)
        .flowOn(Dispatchers.Default)
        .conflate()

    suspend fun insertMovies(movies: List<MovieEntity>) = mMovieDao.insertMovie(movies)

    fun setMovieFavorite(movie: MovieEntity, newState: Boolean) {
        movie.favorite = newState
        mMovieDao.updateFavoriteMovie(movie)
    }
}