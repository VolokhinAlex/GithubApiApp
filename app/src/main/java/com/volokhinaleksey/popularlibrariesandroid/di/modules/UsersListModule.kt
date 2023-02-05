package com.volokhinaleksey.popularlibrariesandroid.di.modules

import com.volokhinaleksey.popularlibrariesandroid.ui.items.IUserListPresenter
import com.volokhinaleksey.popularlibrariesandroid.ui.screens.users.UsersPresenter.UsersListPresenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UsersListModule {

    @Singleton
    @Provides
    fun usersListPresenter(): IUserListPresenter = UsersListPresenter()

}