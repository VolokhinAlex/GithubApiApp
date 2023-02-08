package com.volokhinaleksey.popularlibrariesandroid.di.modules

import com.volokhinaleksey.popularlibrariesandroid.app.App
import com.volokhinaleksey.popularlibrariesandroid.di.scopes.UserScope
import com.volokhinaleksey.popularlibrariesandroid.di.scopes.UserScopeContainer
import com.volokhinaleksey.popularlibrariesandroid.repository.*
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface UserModule {

    @UserScope
    @Binds
    fun userRepository(userRepositoryImpl: GithubUserRepositoryImpl): GithubUserRepository

    @UserScope
    @Binds
    fun userCache(userCacheImpl: RoomUserCacheImpl): UserCache

    @UserScope
    @Binds
    fun userRepoCommitsCache(commitsCacheImpl: GithubRepoCommits): CommitsCache

    @UserScope
    @Binds
    fun userRepoCommits(repositoryCommitsImpl: GithubRepositoryCommitsImpl): GithubRepositoryCommits

    companion object {
        @UserScope
        @Provides
        fun scopeContainer(app: App): UserScopeContainer = app
    }
}