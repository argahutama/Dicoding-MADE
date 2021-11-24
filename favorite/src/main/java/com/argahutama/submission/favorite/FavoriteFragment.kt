package com.argahutama.submission.favorite

import android.os.Bundle
import android.view.View
import com.argahutama.submission.core.base.BaseFragment
import com.argahutama.submission.favorite.adapter.SectionPagerAdapter
import com.argahutama.submission.favorite.databinding.FragmentFavoriteBinding
import com.argahutama.submission.favorite.di.favoriteModule
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteFragment: BaseFragment() {
    override val viewModel by viewModel<FavoriteViewModel>()
    override fun createBinding() =  FragmentFavoriteBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadKoinModules(favoriteModule)

        with(binding as FragmentFavoriteBinding) {
            viewPager.adapter = SectionPagerAdapter(requireContext(), childFragmentManager)
            tab.setupWithViewPager(viewPager)
        }
    }

    override fun initView() {}

    override fun initAction() {}
}