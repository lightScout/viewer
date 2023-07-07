package com.sample.di

import android.app.Application
import com.sample.model.network.retrofit.RetrofitFactory
import com.sample.model.network.service.DuckDuckGoService
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.dsl.module


class MainApplication : Application() {

    private val appModule = module {
        single { RetrofitFactory() }
        single { DuckDuckGoService(get()) }
    }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@MainApplication)
            modules(appModule)
        }
    }

}