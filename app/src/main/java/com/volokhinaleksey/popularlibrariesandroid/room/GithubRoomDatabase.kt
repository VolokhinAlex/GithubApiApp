package com.volokhinaleksey.popularlibrariesandroid.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.volokhinaleksey.popularlibrariesandroid.model.RoomGithubUser
import com.volokhinaleksey.popularlibrariesandroid.model.RoomGithubUserRepo

/**
 * A class for combining Dao objects with Entity models
 */

@Database(
    entities = [RoomGithubUser::class, RoomGithubUserRepo::class],
    version = 2,
    exportSchema = false
)
abstract class GithubRoomDatabase : RoomDatabase() {

    abstract val userDao: UserDao
    abstract val repositoryDao: RepositoryDao

}