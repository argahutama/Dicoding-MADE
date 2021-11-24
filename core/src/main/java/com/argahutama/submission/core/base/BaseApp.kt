package com.argahutama.submission.core.base

import android.app.Activity
import android.content.Context
import androidx.multidex.MultiDexApplication
import com.argahutama.submission.core.navigation.NavigationDirection

abstract class BaseApp: MultiDexApplication() {
    abstract fun navigateTo(context: Context, direction: NavigationDirection)
    abstract fun navigateTo(
        activity: Activity,
        direction: NavigationDirection,
        requestCode: Int
    )
}