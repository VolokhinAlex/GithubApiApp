package com.volokhinaleksey.popularlibrariesandroid.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

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
    @ColumnInfo(name = "image_url_from_storage")
    val imageUrlFromStorage: String?,
    val url: String?,
    @ColumnInfo(name = "repos_url")
    val reposUrl: String?,
)
