package com.volokhinaleksey.popularlibrariesandroid.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
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

    companion object {
        private const val DB_NAME = "github.db"
        private var instance: GithubRoomDatabase? = null
        fun getInstance() = instance
            ?: throw RuntimeException("Database has not been created. Please call create(context)")

        fun createDatabase(context: Context?) {
            if (instance == null) {
                instance =
                    Room.databaseBuilder(
                        context!!,
                        GithubRoomDatabase::class.java,
                        DB_NAME
                    ).build()
            }
        }
    }

}