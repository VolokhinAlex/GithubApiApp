package com.volokhinaleksey.popularlibrariesandroid.di.modules

import com.volokhinaleksey.popularlibrariesandroid.app.App
import com.volokhinaleksey.popularlibrariesandroid.di.scopes.UsersScope
import com.volokhinaleksey.popularlibrariesandroid.di.scopes.UsersScopeContainer
import com.volokhinaleksey.popularlibrariesandroid.repository.GithubUsersRepository
import com.volokhinaleksey.popularlibrariesandroid.repository.GithubUsersRepositoryImpl
import com.volokhinaleksey.popularlibrariesandroid.repository.RoomGithubUsersCacheImpl
import com.volokhinaleksey.popularlibrariesandroid.repository.UsersCache
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface UsersModule {

    @UsersScope
    @Binds
    fun usersCache(usersCacheImpl: RoomGithubUsersCacheImpl): UsersCache

    @UsersScope
    @Binds
    fun usersRepository(usersRepositoryImpl: GithubUsersRepositoryImpl): GithubUsersRepository

    companion object {
        @UsersScope
        @Provides
        fun scopeContainer(app: App): UsersScopeContainer = app
    }
}