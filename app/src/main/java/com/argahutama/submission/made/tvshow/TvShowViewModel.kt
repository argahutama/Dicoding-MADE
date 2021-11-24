package com.argahutama.submission.made.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.argahutama.submission.core.base.BaseViewModel
import com.argahutama.submission.core.data.Resource
import com.argahutama.submission.core.domain.model.Movie
import com.argahutama.submission.core.domain.usecase.MovieUseCase

class TvShowViewModel(private val movieUseCase: MovieUseCase): BaseViewModel() {
    fun getTvShows(): LiveData<Resource<List<Movie>>> = movieUseCase.getAllTvShows().asLiveData()
}