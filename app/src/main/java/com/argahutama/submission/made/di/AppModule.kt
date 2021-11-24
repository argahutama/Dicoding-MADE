package com.argahutama.submission.made.di

import com.argahutama.submission.core.domain.usecase.MovieInteractor
import com.argahutama.submission.core.domain.usecase.MovieUseCase
import com.argahutama.submission.made.detail.DetailViewModel
import com.argahutama.submission.made.main.MainViewModel
import com.argahutama.submission.made.movie.MovieViewModel
import com.argahutama.submission.made.tvshow.TvShowViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module


val useCaseModule = module {
    factory<MovieUseCase> { MovieInteractor(get()) }
}

@ExperimentalCoroutinesApi
@FlowPreview
val viewModelModule = module {
    viewModel { MovieViewModel(get()) }
    viewModel { TvShowViewModel(get()) }
    viewModel { DetailViewModel(get()) }
    viewModel { MainViewModel(get()) }
}