package com.argahutama.submission.favorite.tvshow

import com.argahutama.submission.core.base.BaseFragment
import com.argahutama.submission.favorite.FavoriteViewModel
import com.argahutama.submission.favorite.databinding.FragmentFavoriteTvShowBinding
import org.koin.android.viewmodel.ext.android.sharedViewModel

class FavoriteTvShowFragment: BaseFragment() {
    override val viewModel by sharedViewModel<FavoriteViewModel>()
    override fun createBinding() = FragmentFavoriteTvShowBinding.inflate(layoutInflater)

    override fun initView() {}

    override fun initAction() {}
}