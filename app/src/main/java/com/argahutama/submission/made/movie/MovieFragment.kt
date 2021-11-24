package com.argahutama.submission.made.movie

import com.argahutama.submission.core.base.BaseFragment
import com.argahutama.submission.made.databinding.FragmentMovieBinding
import org.koin.android.viewmodel.ext.android.viewModel

class MovieFragment: BaseFragment() {
    override val viewModel by viewModel<MovieViewModel>()
    override fun createBinding() = FragmentMovieBinding.inflate(layoutInflater)

    override fun initView() {}

    override fun initAction() {}
}