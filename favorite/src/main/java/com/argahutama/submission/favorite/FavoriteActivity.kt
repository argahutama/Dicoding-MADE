package com.argahutama.submission.favorite

import com.argahutama.submission.core.base.BaseActivity
import com.argahutama.submission.favorite.databinding.ActivityFavoriteBinding
import org.koin.android.viewmodel.ext.android.viewModel

class FavoriteActivity: BaseActivity() {
    override val binding by lazy { ActivityFavoriteBinding.inflate(layoutInflater) }
    override val viewModel by viewModel<FavoriteViewModel>()

    override fun initView() {}

    override fun initAction() {}
}