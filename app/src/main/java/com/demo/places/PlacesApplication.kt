package com.demo.places

import android.app.Application
import com.demo.places.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class PlacesApplication: Application(){

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(applicationContext)
            modules(listOf(interactorModule, localModule, networkModule, repositoryModule,
                viewModelModule))
        }
    }
}