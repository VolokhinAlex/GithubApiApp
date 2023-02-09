package com.volokhinaleksey.popularlibrariesandroid.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.volokhinaleksey.popularlibrariesandroid.model.RoomGithubRepoCommits

@Dao
interface CommitsDao {

    @Upsert
    fun upsert(commit: RoomGithubRepoCommits)

    @Upsert
    fun upsert(commits: List<RoomGithubRepoCommits>)

    @Delete
    fun delete(commit: RoomGithubRepoCommits)

    @Query("SELECT * FROM github_repo_commits WHERE repository_id = :repoId")
    fun getCommitsByRepoId(repoId: Long): List<RoomGithubRepoCommits>

}