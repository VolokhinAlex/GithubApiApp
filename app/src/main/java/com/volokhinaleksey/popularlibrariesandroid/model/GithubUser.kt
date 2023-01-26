package com.volokhinaleksey.popularlibrariesandroid.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize

@Parcelize
data class GithubUser(
    @Expose
    val login: String?,
    @Expose
    val id: Long?,
    @Expose
    val avatarUrl: String?,
    @Expose
    val publicRepos: Long?,
    @Expose
    val publicGists: Long?,
    @Expose
    val followers: Long?,
    @Expose
    val following: Long?,
    @Expose
    val name: String?,
    @Expose
    val company: String?,
    @Expose
    val blog: String?,
    @Expose
    val location: String?
) : Parcelable
