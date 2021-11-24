package com.argahutama.submission.core.base

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.argahutama.submission.core.navigation.NavigationDirection
import com.argahutama.submission.custom_ui.CustomSnack

@Suppress("DEPRECATION")
abstract class BaseActivity : AppCompatActivity() {
    open val fullscreen = false
    abstract val binding: ViewBinding
    abstract val viewModel: BaseViewModel
    abstract fun initView()
    abstract fun initAction()

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        if (fullscreen) {
            window.decorView.systemUiVisibility =
                (View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
            window.statusBarColor = Color.TRANSPARENT
        }

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        setup()
    }

    open fun setup() {
        initView()
        initAction()
    }

    fun getBaseApp() = application as? BaseApp

    fun showSnackbar(message: String, type: Int = CustomSnack.SUCCESS) =
        CustomSnack.show(this, message, type, binding.root)

    fun navigateTo(direction: NavigationDirection, requestCode: Int? = null) =
        if (requestCode == null) getBaseApp()?.navigateTo(this, direction)
        else getBaseApp()?.navigateTo(this, direction, requestCode)
}