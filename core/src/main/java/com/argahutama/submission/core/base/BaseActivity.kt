package com.argahutama.submission.core.base

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.argahutama.submission.custom_ui.CustomSnack

abstract class BaseActivity : AppCompatActivity() {
    abstract val binding: ViewBinding
    abstract val viewModel: BaseViewModel
    abstract fun initView()
    abstract fun initAction()

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        setup()
    }

    open fun setup() {
        initView()
        initAction()
    }

    fun showSnackbar(message: String, type: Int) =
        CustomSnack.show(this, message, type, binding.root)
}