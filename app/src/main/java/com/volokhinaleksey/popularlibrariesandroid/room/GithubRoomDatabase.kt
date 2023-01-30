package com.volokhinaleksey.popularlibrariesandroid.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.volokhinaleksey.popularlibrariesandroid.model.RoomGithubUser
import com.volokhinaleksey.popularlibrariesandroid.model.RoomGithubUserRepo

@Database(
    entities = [RoomGithubUser::class, RoomGithubUserRepo::class],
    version = 1,
    exportSchema = false
)
abstract class GithubRoomDatabase : RoomDatabase() {

    abstract val userDao: UserDao
    abstract val repositoryDao: RepositoryDao

}