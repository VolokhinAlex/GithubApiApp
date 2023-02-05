package com.volokhinaleksey.popularlibrariesandroid.app

import android.app.Application
import com.volokhinaleksey.popularlibrariesandroid.di.components.AppComponent
import com.volokhinaleksey.popularlibrariesandroid.di.components.DaggerAppComponent
import com.volokhinaleksey.popularlibrariesandroid.di.modules.AppModule
import timber.log.Timber

class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appInstance = this
        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
        Timber.plant(Timber.DebugTree())
    }

    companion object {
        lateinit var appInstance: App
    }

}