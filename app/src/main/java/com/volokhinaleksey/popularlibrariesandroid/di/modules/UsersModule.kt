package com.volokhinaleksey.popularlibrariesandroid.di.modules

import com.volokhinaleksey.popularlibrariesandroid.app.App
import com.volokhinaleksey.popularlibrariesandroid.di.scopes.UsersScope
import com.volokhinaleksey.popularlibrariesandroid.di.scopes.UsersScopeContainer
import com.volokhinaleksey.popularlibrariesandroid.repository.GithubUsersRepository
import com.volokhinaleksey.popularlibrariesandroid.repository.GithubUsersRepositoryImpl
import com.volokhinaleksey.popularlibrariesandroid.repository.RoomGithubUsersCacheImpl
import com.volokhinaleksey.popularlibrariesandroid.repository.UsersCache
import com.volokhinaleksey.popularlibrariesandroid.ui.items.IUserListPresenter
import com.volokhinaleksey.popularlibrariesandroid.ui.screens.users.UsersPresenter
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface UsersModule {

    @UsersScope
    @Binds
    fun usersCache(impl: RoomGithubUsersCacheImpl): UsersCache

    @UsersScope
    @Binds
    fun usersRepository(impl: GithubUsersRepositoryImpl): GithubUsersRepository

    companion object {
        @UsersScope
        @Provides
        fun scopeContainer(app: App): UsersScopeContainer = app

        @UsersScope
        @Provides
        fun usersListPresenter(): IUserListPresenter = UsersPresenter.UsersListPresenter()
    }
}