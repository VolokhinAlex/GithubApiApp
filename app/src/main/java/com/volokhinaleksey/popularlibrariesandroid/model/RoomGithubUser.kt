package com.volokhinaleksey.popularlibrariesandroid.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * A data model that comes from a local repository.
 * The class is used  for Local Database.
 * The class is the parent class for the RoomGithubUserRepo class
 *
 * @param login - User login.
 * @param id - User id.
 * @param avatarUrl - User image profile.
 * @param publicRepos - Number of user's open repositories.
 * @param publicGists - Number of user's gists.
 * @param followers - The number of followers the user has.
 * @param following - The number of following the user has.
 * @param name - Full user name.
 * @param company - The company the user works for.
 * @param blog - User personal blog name.
 * @param location - User location.
 *
 */

@Entity(tableName = "github_user")
data class RoomGithubUser(
    @PrimaryKey val id: Long?,
    val login: String?,
    @ColumnInfo(name = "avatar_url")
    val avatarUrl: String?,
    @ColumnInfo(name = "public_repos")
    val publicRepos: Long?,
    @ColumnInfo(name = "public_gists")
    val publicGists: Long?,
    val followers: Long?,
    val following: Long?,
    val name: String?,
    val company: String?,
    val blog: String?,
    val location: String?,
    val url: String?,
    @ColumnInfo(name = "repos_url")
    val reposUrl: String?,
)
