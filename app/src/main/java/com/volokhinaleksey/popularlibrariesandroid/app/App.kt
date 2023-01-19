package com.volokhinaleksey.popularlibrariesandroid.app

import android.app.Application
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router

class App : Application() {

    private val cicerone: Cicerone<Router> by lazy {
        Cicerone.create()
    }

    val navigatorHolder get() = cicerone.getNavigatorHolder()
    val router get() = cicerone.router

    override fun onCreate() {
        super.onCreate()
        appInstance = this
    }

    companion object {
        lateinit var appInstance: App
    }

}