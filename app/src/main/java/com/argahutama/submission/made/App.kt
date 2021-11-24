package com.argahutama.submission.made

import android.app.Application
import com.argahutama.submission.core.di.dbModule
import com.argahutama.submission.core.di.networkModule
import com.argahutama.submission.core.di.repositoryModule
import com.argahutama.submission.made.di.useCaseModule
import com.argahutama.submission.made.di.viewModelModule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

@FlowPreview
@ExperimentalCoroutinesApi
class App : Application() {
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
    }
}