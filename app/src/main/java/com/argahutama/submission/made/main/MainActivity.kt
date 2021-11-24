package com.argahutama.submission.made.main

import com.argahutama.submission.core.base.BaseActivity
import com.argahutama.submission.made.databinding.ActivityMainBinding
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity() {
    override val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override val viewModel by viewModel<MainViewModel>()

    override fun initView() {}

    override fun initAction() {}
}