package com.argahutama.submission.made

import android.annotation.SuppressLint
import com.argahutama.submission.core.base.BaseActivity
import com.argahutama.submission.made.databinding.ActivitySplashBinding
import org.koin.android.viewmodel.ext.android.viewModel

@SuppressLint("CustomSplashScreen")
class SplashActivity : BaseActivity() {
    override val binding by lazy { ActivitySplashBinding.inflate(layoutInflater) }
    override val viewModel by viewModel<SplashViewModel>()
    override fun initView() {}

    override fun initAction() {}
}