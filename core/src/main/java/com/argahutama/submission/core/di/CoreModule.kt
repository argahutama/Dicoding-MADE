package com.argahutama.submission.core.di

import androidx.room.Room
import com.argahutama.submission.core.BuildConfig
import com.argahutama.submission.core.data.MovieRepository
import com.argahutama.submission.core.data.source.local.LocalDataSource
import com.argahutama.submission.core.data.source.local.room.MovieDb
import com.argahutama.submission.core.data.source.remote.RemoteDataSource
import com.argahutama.submission.core.data.source.remote.network.ApiService
import com.argahutama.submission.core.domain.repository.IMovieRepository
import com.argahutama.submission.core.util.AppExecutors
import com.ashokvarma.gander.GanderInterceptor
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val dbModule = module {
    factory { get<MovieDb>().movieDao() }
    single {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("argahutama".toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(
            androidContext(),
            MovieDb::class.java, "movie.db"
        ).fallbackToDestructiveMigration().openHelperFactory(factory).build()
    }
}

val networkModule = module {
    single {
        val hostname = "api.themoviedb.org"
        val certificatePinner = CertificatePinner.Builder()
            .add(hostname, "sha256/oD/WAoRPvbez1Y2dfYfuo4yujAcYHXdv1Ivb2v2MOKk=")
            .build()
        OkHttpClient.Builder()
            .addInterceptor(GanderInterceptor(androidContext()).showNotification(true))
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(40, TimeUnit.SECONDS)
            .readTimeout(40, TimeUnit.SECONDS)
            .certificatePinner(certificatePinner)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<IMovieRepository> { MovieRepository(get(), get(), get()) }
}