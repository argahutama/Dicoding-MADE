package com.argahutama.submission.core.domain.usecase

import com.argahutama.submission.core.data.Resource
import com.argahutama.submission.core.domain.model.Movie
import com.argahutama.submission.core.domain.repository.IMovieRepository
import kotlinx.coroutines.flow.Flow

class MovieInteractor(private val iMovieAppRepository: IMovieRepository) : MovieUseCase {
    override fun getAllMovies(): Flow<Resource<List<Movie>>> = iMovieAppRepository.getAllMovies()

    override fun getAllTvShows(): Flow<Resource<List<Movie>>> = iMovieAppRepository.getAllTvShows()

    override fun getFavoriteMovies(): Flow<List<Movie>> = iMovieAppRepository.getFavoriteMovies()

    override fun searchMovies(search: String): Flow<List<Movie>> =
        iMovieAppRepository.searchMovies(search)

    override fun searchTvShows(search: String): Flow<List<Movie>> =
        iMovieAppRepository.searchTvShows(search)

    override fun getFavoriteTvShows(): Flow<List<Movie>> = iMovieAppRepository.getFavoriteTvShows()

    override fun setMovieFavorite(movie: Movie, state: Boolean) =
        iMovieAppRepository.setMovieFavorite(movie, state)
}