package com.volokhinaleksey.popularlibrariesandroid.di.modules

import com.volokhinaleksey.popularlibrariesandroid.ui.items.IUserReposListPresenter
import com.volokhinaleksey.popularlibrariesandroid.ui.screens.user.UserPresenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ReposListModule {

    @Singleton
    @Provides
    fun reposListPresenter(): IUserReposListPresenter = UserPresenter.UserReposListPresenter()

}