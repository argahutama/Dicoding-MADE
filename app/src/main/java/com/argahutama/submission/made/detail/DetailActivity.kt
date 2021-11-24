package com.argahutama.submission.made.detail

import com.argahutama.submission.core.base.BaseActivity
import com.argahutama.submission.made.databinding.ActivityDetailBinding
import org.koin.android.viewmodel.ext.android.viewModel

class DetailActivity : BaseActivity() {
    override val binding by lazy { ActivityDetailBinding.inflate(layoutInflater) }
    override val viewModel by viewModel<DetailViewModel>()

    override fun initView() {}

    override fun initAction() {}

}