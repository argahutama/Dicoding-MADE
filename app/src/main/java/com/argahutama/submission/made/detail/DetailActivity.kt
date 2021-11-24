package com.argahutama.submission.made.detail

import com.argahutama.submission.core.base.BaseActivity
import com.argahutama.submission.core.domain.model.Movie
import com.argahutama.submission.core.navigation.Extra
import com.argahutama.submission.made.databinding.ActivityDetailBinding
import org.koin.android.viewmodel.ext.android.viewModel

class DetailActivity : BaseActivity() {
    private var movie: Movie? = null
    override val binding by lazy { ActivityDetailBinding.inflate(layoutInflater) }
    override val viewModel by viewModel<DetailViewModel>()

    override fun setup() {
        loadArgs()
        super.setup()
    }

    override fun initView() {}

    override fun initAction() {}

    private fun loadArgs() {
        movie = intent.getParcelableExtra(Extra.MOVIE)
    }
}