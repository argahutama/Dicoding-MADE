package com.argahutama.submission.made

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import com.argahutama.submission.core.base.BaseApp
import com.argahutama.submission.core.di.dbModule
import com.argahutama.submission.core.di.networkModule
import com.argahutama.submission.core.di.repositoryModule
import com.argahutama.submission.core.navigation.NavigationDirection
import com.argahutama.submission.made.di.useCaseModule
import com.argahutama.submission.made.di.viewModelModule
import com.ashokvarma.gander.Gander
import com.ashokvarma.gander.imdb.GanderIMDB
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import java.io.Serializable

@FlowPreview
@ExperimentalCoroutinesApi
class App : BaseApp() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@App)
            modules(
                listOf(
                    dbModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
        if (BuildConfig.DEBUG) Gander.setGanderStorage(GanderIMDB.getInstance())
    }

    override fun navigateTo(context: Context, direction: NavigationDirection) {
        Intent(context, navigationMapper[direction::class.java])
            .apply { direction.extras.forEach { putExtra(it) } }
            .also { context.startActivity(it) }
    }

    override fun navigateTo(activity: Activity, direction: NavigationDirection, requestCode: Int) {
        Intent(activity, navigationMapper[direction::class.java])
            .apply { direction.extras.forEach { putExtra(it) } }
            .also { activity.startActivityForResult(it, requestCode) }
    }

    private fun Intent.putExtra(it: Map.Entry<String, Any?>) = when (val value = it.value) {
        is Int -> putExtra(it.key, value)
        is Long -> putExtra(it.key, value)
        is CharSequence -> putExtra(it.key, value)
        is String -> putExtra(it.key, value)
        is Float -> putExtra(it.key, value)
        is Double -> putExtra(it.key, value)
        is Char -> putExtra(it.key, value)
        is Short -> putExtra(it.key, value)
        is Boolean -> putExtra(it.key, value)
        is Serializable -> putExtra(it.key, value)
        is Bundle -> putExtra(it.key, value)
        is Parcelable -> putExtra(it.key, value)
        is Array<*> -> when {
            value.isArrayOf<CharSequence>() -> putExtra(it.key, value)
            value.isArrayOf<String>() -> putExtra(it.key, value)
            value.isArrayOf<Parcelable>() -> putExtra(it.key, value)
            else -> throw Exception("Intent extra ${it.key} has wrong type ${value.javaClass.name}")
        }
        is IntArray -> putExtra(it.key, value)
        is LongArray -> putExtra(it.key, value)
        is FloatArray -> putExtra(it.key, value)
        is DoubleArray -> putExtra(it.key, value)
        is CharArray -> putExtra(it.key, value)
        is ShortArray -> putExtra(it.key, value)
        is BooleanArray -> putExtra(it.key, value)
        else -> throw Exception("Intent extra ${it.key} has wrong type ${value?.javaClass?.name}")
    }
}