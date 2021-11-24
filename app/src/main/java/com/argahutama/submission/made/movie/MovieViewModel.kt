package com.argahutama.submission.made.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.argahutama.submission.core.base.BaseViewModel
import com.argahutama.submission.core.data.Resource
import com.argahutama.submission.core.domain.model.Movie
import com.argahutama.submission.core.domain.usecase.MovieUseCase

class MovieViewModel(private val movieUseCase: MovieUseCase) : BaseViewModel() {
    fun getMovies(sort: String): LiveData<Resource<List<Movie>>> =
        movieUseCase.getAllMovies(sort).asLiveData()
}