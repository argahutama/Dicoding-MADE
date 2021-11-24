package com.argahutama.submission.core.data.source.local

import com.argahutama.submission.core.data.source.local.entity.MovieEntity
import com.argahutama.submission.core.data.source.local.room.MovieDao
import com.argahutama.submission.core.util.SortUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn

class LocalDataSource(private val mMovieDao: MovieDao) {
    fun getAllMovies(): Flow<List<MovieEntity>> {
        val query = SortUtil.getSortedQueryMovies()
        return mMovieDao.getMovies(query)
    }

    fun getAllTvShows(): Flow<List<MovieEntity>> {
        val query = SortUtil.getSortedQueryTvShows()
        return mMovieDao.getTvShows(query)
    }

    fun getAllFavoriteMovies(): Flow<List<MovieEntity>> {
        val query = SortUtil.getSortedQueryFavoriteMovies()
        return mMovieDao.getFavoriteMovies(query)
    }

    fun getAllFavoriteTvShows(): Flow<List<MovieEntity>> {
        val query = SortUtil.getSortedQueryFavoriteTvShows()
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