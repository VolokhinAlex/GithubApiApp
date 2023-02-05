package com.volokhinaleksey.popularlibrariesandroid.room

import androidx.room.*
import com.volokhinaleksey.popularlibrariesandroid.model.RoomGithubUser

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: RoomGithubUser)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: List<RoomGithubUser>)

    @Update
    fun update(user: RoomGithubUser)

    @Delete
    fun delete(user: RoomGithubUser)

    @Query("SELECT * FROM github_user")
    fun getAll(): List<RoomGithubUser>

    @Query("SELECT * FROM github_user WHERE login = :login LIMIT 1")
    fun findByLogin(login: String): RoomGithubUser?
}