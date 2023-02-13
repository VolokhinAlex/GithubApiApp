package com.volokhinaleksey.popularlibrariesandroid.di.components

import com.volokhinaleksey.popularlibrariesandroid.app.App
import com.volokhinaleksey.popularlibrariesandroid.di.modules.*
import com.volokhinaleksey.popularlibrariesandroid.ui.activity.MainActivity
import com.volokhinaleksey.popularlibrariesandroid.ui.activity.MainPresenter
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        CiceroneModule::class,
        DatabaseModule::class,
        ApiModule::class,
        SchedulerModule::class,
        ImageLoaderModule::class
    ]
)
interface AppComponent {
    fun usersSubcomponent(): UsersSubcomponent
    fun injectMainActivity(mainActivity: MainActivity)
    fun injectMainPresenter(): MainPresenter

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(app: App): Builder

        fun build(): AppComponent
    }
}