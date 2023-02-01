package com.volokhinaleksey.popularlibrariesandroid.di.modules

import com.volokhinaleksey.popularlibrariesandroid.app.App
import com.volokhinaleksey.popularlibrariesandroid.di.scopes.UserScope
import com.volokhinaleksey.popularlibrariesandroid.di.scopes.UserScopeContainer
import com.volokhinaleksey.popularlibrariesandroid.repository.GithubUserRepository
import com.volokhinaleksey.popularlibrariesandroid.repository.GithubUserRepositoryImpl
import com.volokhinaleksey.popularlibrariesandroid.repository.RoomUserCacheImpl
import com.volokhinaleksey.popularlibrariesandroid.repository.UserCache
import com.volokhinaleksey.popularlibrariesandroid.ui.items.IUserReposListPresenter
import com.volokhinaleksey.popularlibrariesandroid.ui.screens.user.UserPresenter
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface UserModule {

    @UserScope
    @Binds
    fun userRepository(impl: GithubUserRepositoryImpl): GithubUserRepository

    @UserScope
    @Binds
    fun userCache(impl: RoomUserCacheImpl): UserCache

    companion object {
        @UserScope
        @Provides
        fun scopeContainer(app: App): UserScopeContainer = app

        @UserScope
        @Provides
        fun reposListPresenter(): IUserReposListPresenter = UserPresenter.UserReposListPresenter()
    }
}