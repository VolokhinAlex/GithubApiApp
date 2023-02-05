package com.volokhinaleksey.popularlibrariesandroid.room

import androidx.room.*
import com.volokhinaleksey.popularlibrariesandroid.model.RoomGithubUserRepo

@Dao
interface RepositoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: RoomGithubUserRepo)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: List<RoomGithubUserRepo>)

    @Update
    fun update(user: RoomGithubUserRepo)

    @Delete
    fun delete(user: RoomGithubUserRepo)

    @Query("SELECT * FROM github_repository")
    fun getAll(): List<RoomGithubUserRepo>

    @Query("SELECT * FROM github_repository WHERE user_id = :userId")
    fun getRepositoriesByUserId(userId: String): List<RoomGithubUserRepo>

}