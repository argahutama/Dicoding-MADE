package com.argahutama.submission.made.detail

import com.argahutama.submission.core.base.BaseViewModel
import com.argahutama.submission.core.domain.model.Movie
import com.argahutama.submission.core.domain.usecase.MovieUseCase

class DetailViewModel(private val movieUseCase: MovieUseCase): BaseViewModel() {
    fun setFavoriteMovie(movie: Movie, newStatus: Boolean) =
        movieUseCase.setMovieFavorite(movie, newStatus)
}