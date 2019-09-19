package com.allen.weatherapp

import android.app.Application
import com.allen.weatherapp.di.AppModule
import com.allen.weatherapp.di.RemoteModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        if(GlobalContext.getOrNull() == null){
            startKoin {
                androidLogger()
                androidContext(this@App)
                modules(listOf(RemoteModule, AppModule))
            }
        }
    }

}