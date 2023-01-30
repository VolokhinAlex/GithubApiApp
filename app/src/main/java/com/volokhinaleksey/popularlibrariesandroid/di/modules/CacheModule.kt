package com.volokhinaleksey.popularlibrariesandroid.di.modules

import androidx.room.Room
import com.volokhinaleksey.popularlibrariesandroid.app.App
import com.volokhinaleksey.popularlibrariesandroid.repository.RepositoriesCache
import com.volokhinaleksey.popularlibrariesandroid.repository.RoomGithubRepositoriesCacheImpl
import com.volokhinaleksey.popularlibrariesandroid.repository.RoomGithubUsersCacheImpl
import com.volokhinaleksey.popularlibrariesandroid.repository.UsersCache
import com.volokhinaleksey.popularlibrariesandroid.room.GithubRoomDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CacheModule {

    @Singleton
    @Provides
    fun database(app: App): GithubRoomDatabase =
        Room.databaseBuilder(app, GithubRoomDatabase::class.java, DB_NAME).build()

    @Singleton
    @Provides
    fun usersCache(database: GithubRoomDatabase): UsersCache =
        RoomGithubUsersCacheImpl(localDatabase = database)

    @Singleton
    @Provides
    fun repositoriesCache(database: GithubRoomDatabase): RepositoriesCache =
        RoomGithubRepositoriesCacheImpl(database)

    companion object {
        private const val DB_NAME = "github.db"
    }
}