package com.volokhinaleksey.popularlibrariesandroid.di.modules

import androidx.room.Room
import com.volokhinaleksey.popularlibrariesandroid.app.App
import com.volokhinaleksey.popularlibrariesandroid.room.GithubRoomDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun database(app: App): GithubRoomDatabase =
        Room.databaseBuilder(app, GithubRoomDatabase::class.java, DB_NAME)
            .fallbackToDestructiveMigration().build()

    companion object {
        private const val DB_NAME = "github.db"
    }

}