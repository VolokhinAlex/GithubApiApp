package com.volokhinaleksey.popularlibrariesandroid.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize

/**
 * A data model that comes from a remote repository.
 * The class is used  for GitHub API
 *
 * @param id - repository ID on the Github site.
 * @param name - Repository name on the Github site.
 * @param htmlUrl - Link to the repository in the browser.
 * @param fork - Were the repository photos taken or not.
 * @param createdAt - Repository creation date.
 * @param forks - Number of repository forks.
 *
 */

@Parcelize
data class GithubRepositoryDTO(
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