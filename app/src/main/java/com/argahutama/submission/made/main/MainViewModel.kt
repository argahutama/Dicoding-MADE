package com.argahutama.submission.made.main

import androidx.lifecycle.asLiveData
import com.argahutama.submission.core.base.BaseViewModel
import com.argahutama.submission.core.domain.usecase.MovieUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.*

@FlowPreview
@ExperimentalCoroutinesApi
class MainViewModel(private val movieUseCase: MovieUseCase) : BaseViewModel() {
    private val queryChannel = ConflatedBroadcastChannel<String>()

    fun setSearchQuery(search: String) = queryChannel.offer(search)

    val movieResult = queryChannel.asFlow()
        .debounce(300)
        .distinctUntilChanged()
        .filter { it.trim().isNotEmpty() }
        .flatMapLatest { movieUseCase.getSearchMovies(it) }
        .asLiveData()

    val tvShowResult = queryChannel.asFlow()
        .debounce(300)
        .distinctUntilChanged()
        .filter { it.trim().isNotEmpty() }
        .flatMapLatest { movieUseCase.getSearchTvShows(it) }
        .asLiveData()
}