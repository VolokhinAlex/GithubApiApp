package com.volokhinaleksey.popularlibrariesandroid.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "github_repo_commits", foreignKeys = [ForeignKey(
        entity = RoomGithubUserRepo::class,
        parentColumns = ["id"],
        childColumns = ["repository_id"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class RoomGithubRepoCommits(
    @PrimaryKey
    val sha: String,
    @ColumnInfo(name = "commit_message")
    val commitMessage: String,
    @ColumnInfo(name = "committer_name")
    val committerName: String,
    @ColumnInfo(name = "committer_email")
    val committerEmail: String,
    @ColumnInfo(name = "commit_date")
    val commitDate: String,
    @ColumnInfo(name = "repository_id")
    val repositoryId: Long
)