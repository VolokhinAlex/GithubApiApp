package com.volokhinaleksey.popularlibrariesandroid.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize

@Parcelize
data class GithubUserRepo(
    @Expose
    val id: Long?,
    @Expose
    val name: String?,
    @Expose
    val htmlUrl: String?,
    @Expose
    val fork: Boolean?,
    @Expose
    val createdAt: String?,
    @Expose
    val forks: Long?
) : Parcelable