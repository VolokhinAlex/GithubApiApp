package com.volokhinaleksey.popularlibrariesandroid.room

import androidx.room.*
import com.volokhinaleksey.popularlibrariesandroid.model.RoomGithubUserRepo

/**
 * Interface for interacting with a local database table
 */

@Dao
interface RepositoryDao {

    @Upsert
    fun upsert(user: RoomGithubUserRepo)

    @Upsert
    fun upsert(user: List<RoomGithubUserRepo>)

    @Delete
    fun delete(user: RoomGithubUserRepo)

    @Query("SELECT * FROM github_repository")
    fun getAll(): List<RoomGithubUserRepo>

    @Query("SELECT * FROM github_repository WHERE user_id = :userId")
    fun getRepositoriesByUserId(userId: String): List<RoomGithubUserRepo>

}