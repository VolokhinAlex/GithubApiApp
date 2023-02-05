package com.volokhinaleksey.popularlibrariesandroid.di.modules

import com.volokhinaleksey.popularlibrariesandroid.repository.*
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface RepositoryModule {

    @Singleton
    @Binds
    fun usersRepository(impl: GithubUsersRepositoryImpl): GithubUsersRepository
    @Singleton
    @Binds
    fun userRepositoriesRepository(impl: GithubRepositoriesRepositoryImpl): GithubRepositoriesRepository

}