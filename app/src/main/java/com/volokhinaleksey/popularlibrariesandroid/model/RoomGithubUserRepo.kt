package com.volokhinaleksey.popularlibrariesandroid.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "github_repository",
    foreignKeys = [ForeignKey(
        entity = RoomGithubUser::class,
        parentColumns = ["id"],
        childColumns = ["user_id"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class RoomGithubUserRepo(
    @PrimaryKey
    val id: Long,
    @ColumnInfo(name = "user_id")
    val userId: Long,
    val name: String,
    @ColumnInfo(name = "forks_count")
    val forksCount: Long,
    @ColumnInfo(name = "html_url")
    val htmlUrl: String?,
    val fork: Boolean?,
    @ColumnInfo(name = "created_at")
    val createdAt: String?,
)