package com.volokhinaleksey.popularlibrariesandroid.di.modules

import androidx.room.Room
import com.volokhinaleksey.popularlibrariesandroid.app.App
import com.volokhinaleksey.popularlibrariesandroid.repository.RepositoriesCache
import com.volokhinaleksey.popularlibrariesandroid.repository.RoomGithubRepositoriesCacheImpl
import com.volokhinaleksey.popularlibrariesandroid.repository.RoomGithubUsersCacheImpl
import com.volokhinaleksey.popularlibrariesandroid.repository.UsersCache
import com.volokhinaleksey.popularlibrariesandroid.room.GithubRoomDatabase
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
interface CacheModule {

    @Singleton
    @Binds
    fun usersCache(impl: RoomGithubUsersCacheImpl): UsersCache

    @Singleton
    @Binds
    fun repositoriesCache(impl: RoomGithubRepositoriesCacheImpl): RepositoriesCache

    companion object {
        private const val DB_NAME = "github.db"

        @Singleton
        @Provides
        fun database(app: App): GithubRoomDatabase =
            Room.databaseBuilder(app, GithubRoomDatabase::class.java, DB_NAME)
                .fallbackToDestructiveMigration().build()
    }
}