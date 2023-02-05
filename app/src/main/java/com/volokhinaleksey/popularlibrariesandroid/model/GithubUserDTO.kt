package com.volokhinaleksey.popularlibrariesandroid.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize

/**
 * A data model that comes from a remote repository.
 * The class is used  for GitHub API
 *
 * @param login - User login on the Github site.
 * @param id - User id on the Github site.
 * @param avatarUrl - User image profile on the Github site.
 * @param publicRepos - Number of user's open repositories on the Github site.
 * @param publicGists - Number of user's gists on the Github site.
 * @param followers - The number of followers the user has on the Github site.
 * @param following - The number of following the user has on the Github site.
 * @param name - Full user name on the Github site.
 * @param company - The company the user works for.
 * @param blog - User personal blog name on the Github site.
 * @param location - User location on the Github site.
 *
 */

@Parcelize
data class GithubUserDTO(
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
    val location: String?,
    @Expose
    val url: String?,
    @Expose
    val reposUrl: String?,
    var imageUrlFromStorage: String?
) : Parcelable
