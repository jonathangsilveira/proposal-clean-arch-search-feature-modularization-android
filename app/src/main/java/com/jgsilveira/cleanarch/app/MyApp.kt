package com.jgsilveira.cleanarch.app

import android.app.Application
import com.jgsilveira.cleanarch.search.impl.di.searchKoinModule
import org.koin.core.context.startKoin

class MyApp: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(searchKoinModule)
        }
    }
}