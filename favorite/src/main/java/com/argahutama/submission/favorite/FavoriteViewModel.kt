package com.argahutama.submission.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.argahutama.submission.core.base.BaseViewModel
import com.argahutama.submission.core.domain.model.Movie
import com.argahutama.submission.core.domain.usecase.MovieUseCase

class FavoriteViewModel(private val movieUseCase: MovieUseCase) : BaseViewModel() {
    fun getFavoriteMovies(sort: String): LiveData<List<Movie>> =
        movieUseCase.getFavoriteMovies(sort).asLiveData()

    fun getFavoriteTvShows(sort: String): LiveData<List<Movie>> =
        movieUseCase.getFavoriteTvShows(sort).asLiveData()

    fun setFavorite(Movie: Movie, newState: Boolean) =
        movieUseCase.setMovieFavorite(Movie, newState)
}