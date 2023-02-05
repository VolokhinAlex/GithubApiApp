package com.volokhinaleksey.popularlibrariesandroid.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

/**
 * A data model that comes from a local repository.
 * The class is used  for Local Database
 * This class has a one- to-many relationship
 * Parent column in the database table - "id"      [RoomGithubUser]
 * Child column in the database table - "user_id" [userId]
 *
 * @param id - repository ID.
 * @param name - Repository name.
 * @param htmlUrl - Link to the repository in the browser.
 * @param fork - Were the repository photos taken or not.
 * @param createdAt - Repository creation date.
 * @param forksCount - Number of repository forks.
 * @param userId - The user ID that is taken from the RoomGithubUser table
 *
 */

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