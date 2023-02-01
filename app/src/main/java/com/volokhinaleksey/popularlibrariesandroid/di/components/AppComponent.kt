package com.volokhinaleksey.popularlibrariesandroid.di.components

import com.volokhinaleksey.popularlibrariesandroid.di.modules.*
import com.volokhinaleksey.popularlibrariesandroid.ui.activity.MainActivity
import com.volokhinaleksey.popularlibrariesandroid.ui.activity.MainPresenter
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        CiceroneModule::class,
        DatabaseModule::class,
        ApiModule::class,
        SchedulerModule::class,
        ImageLoaderModule::class
    ]
)
interface AppComponent {
    fun usersSubcomponent(): UsersSubcomponent
    fun inject(mainActivity: MainActivity)
    fun injectMainPresenter(): MainPresenter
}