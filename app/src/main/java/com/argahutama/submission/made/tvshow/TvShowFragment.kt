package com.argahutama.submission.made.tvshow

import com.argahutama.submission.core.base.BaseFragment
import com.argahutama.submission.made.databinding.FragmentTvShowBinding
import org.koin.android.viewmodel.ext.android.viewModel

class TvShowFragment: BaseFragment() {
    override val viewModel by viewModel<TvShowViewModel>()
    override fun createBinding() = FragmentTvShowBinding.inflate(layoutInflater)

    override fun initView() {}

    override fun initAction() {}
}