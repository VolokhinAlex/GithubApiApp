package com.volokhinaleksey.popularlibrariesandroid.room

import androidx.room.*
import com.volokhinaleksey.popularlibrariesandroid.model.RoomGithubUserRepo

/**
 * Interface for interacting with a local database table
 */

@Dao
interface RepositoryDao {

    @Upsert
    fun upsert(repo: RoomGithubUserRepo)

    @Upsert
    fun upsert(repos: List<RoomGithubUserRepo>)

    @Delete
    fun delete(repo: RoomGithubUserRepo)

    @Query("SELECT * FROM github_repository WHERE user_id = :userId")
    fun getRepositoriesByUserId(userId: String): List<RoomGithubUserRepo>

}