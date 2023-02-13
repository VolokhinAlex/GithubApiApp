package com.volokhinaleksey.popularlibrariesandroid.app

import android.app.Application
import com.volokhinaleksey.popularlibrariesandroid.BuildConfig
import com.volokhinaleksey.popularlibrariesandroid.di.components.AppComponent
import com.volokhinaleksey.popularlibrariesandroid.di.components.DaggerAppComponent
import com.volokhinaleksey.popularlibrariesandroid.di.components.UserSubcomponent
import com.volokhinaleksey.popularlibrariesandroid.di.components.UsersSubcomponent
import com.volokhinaleksey.popularlibrariesandroid.di.modules.AppModule
import com.volokhinaleksey.popularlibrariesandroid.di.scopes.UserScopeContainer
import com.volokhinaleksey.popularlibrariesandroid.di.scopes.UsersScopeContainer
import timber.log.Timber

class App : Application(), UserScopeContainer, UsersScopeContainer {

    private var _appComponent: AppComponent? = null
    internal val appComponent: AppComponent
        get() = checkNotNull(_appComponent) {
            "AppComponent is not initialized"
        }

    var usersSubcomponent: UsersSubcomponent? = null
        private set

    var userSubcomponent: UserSubcomponent? = null
        private set

    override fun onCreate() {
        super.onCreate()
        appInstance = this
        _appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    companion object {
        lateinit var appInstance: App
    }

    fun initUserSubcomponent() = appComponent.usersSubcomponent().userSubcomponent().also {
        userSubcomponent = it
    }

    fun initUsersSubcomponent() = appComponent.usersSubcomponent().also {
        usersSubcomponent = it
    }

    override fun releaseUserScope() {
        userSubcomponent = null
    }

    override fun releaseUsersScope() {
        usersSubcomponent = null
    }

}