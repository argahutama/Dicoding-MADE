package com.argahutama.submission.favorite.movie

import com.argahutama.submission.core.base.BaseFragment
import com.argahutama.submission.favorite.FavoriteViewModel
import com.argahutama.submission.favorite.databinding.FragmentFavoriteMovieBinding
import org.koin.android.viewmodel.ext.android.sharedViewModel

class FavoriteMovieFragment: BaseFragment() {
    override val viewModel by sharedViewModel<FavoriteViewModel>()
    override fun createBinding() = FragmentFavoriteMovieBinding.inflate(layoutInflater)

    override fun initView() {}

    override fun initAction() {}
}