package com.volokhinaleksey.popularlibrariesandroid.di.components

import com.volokhinaleksey.popularlibrariesandroid.di.modules.*
import com.volokhinaleksey.popularlibrariesandroid.ui.activity.MainActivity
import com.volokhinaleksey.popularlibrariesandroid.ui.activity.MainPresenter
import com.volokhinaleksey.popularlibrariesandroid.ui.screens.repo_details.RepoDetailsPresenter
import com.volokhinaleksey.popularlibrariesandroid.ui.screens.user.UserFragment
import com.volokhinaleksey.popularlibrariesandroid.ui.screens.user.UserPresenter
import com.volokhinaleksey.popularlibrariesandroid.ui.screens.users.UsersPresenter
import com.volokhinaleksey.popularlibrariesandroid.ui.screens.users.adapter.UsersAdapter
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        CiceroneModule::class,
        CacheModule::class,
        ApiModule::class,
        RepositoryModule::class,
        SchedulerModule::class,
        UsersListModule::class,
        ImageLoaderModule::class
    ]
)
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(userPresenter: UserPresenter)
    fun inject(userFragment: UserFragment)
    fun injectUsersAdapter(): UsersAdapter

    fun injectUsersPresenter(): UsersPresenter
    fun injectMainPresenter(): MainPresenter
    fun injectRepoDetailsPresenter(): RepoDetailsPresenter
}