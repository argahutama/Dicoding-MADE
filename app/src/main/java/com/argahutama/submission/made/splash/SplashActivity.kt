package com.argahutama.submission.made.splash

import android.annotation.SuppressLint
import androidx.lifecycle.lifecycleScope
import com.argahutama.submission.core.base.BaseActivity
import com.argahutama.submission.core.navigation.NavigationDirection
import com.argahutama.submission.made.databinding.ActivitySplashBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel

@SuppressLint("CustomSplashScreen")
class SplashActivity : BaseActivity() {
    private val delayInMillis = 1500L
    override val fullscreen = true
    override val binding by lazy { ActivitySplashBinding.inflate(layoutInflater) }
    override val viewModel by viewModel<SplashViewModel>()
    override fun initView() {}

    override fun initAction() {
        lifecycleScope.launch {
            delay(delayInMillis)
            navigateTo(NavigationDirection.Main)
            finishAffinity()
        }
    }
}