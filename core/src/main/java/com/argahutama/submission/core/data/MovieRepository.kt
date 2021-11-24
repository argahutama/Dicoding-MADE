package com.argahutama.submission.core.data

import com.argahutama.submission.core.data.source.local.LocalDataSource
import com.argahutama.submission.core.data.source.remote.RemoteDataSource
import com.argahutama.submission.core.data.source.remote.network.ApiResponse
import com.argahutama.submission.core.data.source.remote.response.MovieResponse
import com.argahutama.submission.core.data.source.remote.response.TvShowResponse
import com.argahutama.submission.core.domain.model.Movie
import com.argahutama.submission.core.domain.repository.IMovieRepository
import com.argahutama.submission.core.util.AppExecutors
import com.argahutama.submission.core.util.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MovieRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IMovieRepository {
    override fun getAllMovies(sort: String): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<MovieResponse>>() {
            override fun loadFromDB(): Flow<List<Movie>> = localDataSource.getAllMovies(sort).map {
                DataMapper.mapEntitiesToDomain(it)
            }

            override fun shouldFetch(data: List<Movie>?) = data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getMovies()

            override suspend fun saveCallResult(data: List<MovieResponse>) {
                val movieList = DataMapper.mapMovieResponsesToEntities(data)
                localDataSource.insertMovies(movieList)
            }
        }.asFlow()

    override fun getAllTvShows(sort: String): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<TvShowResponse>>() {
            override fun loadFromDB(): Flow<List<Movie>> = localDataSource.getAllTvShows(sort).map {
                DataMapper.mapEntitiesToDomain(it)
            }

            override fun shouldFetch(data: List<Movie>?) = data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<TvShowResponse>>> =
                remoteDataSource.getTvShows()

            override suspend fun saveCallResult(data: List<TvShowResponse>) {
                val tvShowList = DataMapper.mapTvShowResponsesToEntities(data)
                localDataSource.insertMovies(tvShowList)
            }
        }.asFlow()

    override fun getSearchMovies(search: String): Flow<List<Movie>> =
        localDataSource.getMovieSearch(search).map {
            DataMapper.mapEntitiesToDomain(it)
        }

    override fun getSearchTvShows(search: String): Flow<List<Movie>> =
        localDataSource.getTvShowSearch(search).map {
            DataMapper.mapEntitiesToDomain(it)
        }

    override fun getFavoriteMovies(sort: String): Flow<List<Movie>> =
        localDataSource.getAllFavoriteMovies(sort).map {
            DataMapper.mapEntitiesToDomain(it)
        }

    override fun getFavoriteTvShows(sort: String): Flow<List<Movie>> =
        localDataSource.getAllFavoriteTvShows(sort).map {
            DataMapper.mapEntitiesToDomain(it)
        }

    override fun setMovieFavorite(movie: Movie, state: Boolean) {
        val movieEntity = DataMapper.mapDomainToEntity(movie)
        appExecutors.diskIO().execute { localDataSource.setMovieFavorite(movieEntity, state) }
    }
}