package com.volokhinaleksey.popularlibrariesandroid.di.modules

import com.volokhinaleksey.popularlibrariesandroid.repository.*
import com.volokhinaleksey.popularlibrariesandroid.room.GithubRoomDatabase
import com.volokhinaleksey.popularlibrariesandroid.utils.NetworkStatus
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun usersRepository(
        remoteApiSource: GithubApiService,
        networkStatus: NetworkStatus,
        localDatabase: GithubRoomDatabase,
        roomGithubUsersCache: UsersCache
    ): GithubUsersRepository = GithubUsersRepositoryImpl(
        remoteApiSource = remoteApiSource,
        networkStatus = networkStatus,
        localDatabase = localDatabase,
        roomGithubUsersCache = roomGithubUsersCache
    )

    @Singleton
    @Provides
    fun userRepositoriesRepository(
        remoteApiSource: GithubApiService,
        networkStatus: NetworkStatus,
        localDatabase: GithubRoomDatabase,
        repositoriesCache: RepositoriesCache
    ): GithubRepositoriesRepository =
        GithubRepositoriesRepositoryImpl(
            remoteApiSource = remoteApiSource,
            networkStatus = networkStatus,
            localDatabase = localDatabase,
            repositoriesCache = repositoriesCache
        )
}