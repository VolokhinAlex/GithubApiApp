package com.volokhinaleksey.popularlibrariesandroid.room

import androidx.room.*
import com.volokhinaleksey.popularlibrariesandroid.model.RoomGithubUser

/**
 * Interface for interacting with a local database table
 */
@Dao
interface UserDao {
    @Upsert
    fun upsert(user: RoomGithubUser)

    @Upsert
    fun upsert(user: List<RoomGithubUser>)

    @Delete
    fun delete(user: RoomGithubUser)

    @Query("SELECT * FROM github_user")
    fun getAll(): List<RoomGithubUser>

    @Query("SELECT * FROM github_user WHERE login = :login LIMIT 1")
    fun findByLogin(login: String): RoomGithubUser?
}