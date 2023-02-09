package com.volokhinaleksey.popularlibrariesandroid.model

import com.google.gson.annotations.Expose

data class GithubCommitsDTO(
    @Expose
    val sha: String?,
    @Expose
    val commit: CommitDTO?
)

data class CommitDTO(
    @Expose
    val committer: CommitterDTO?,
    @Expose
    val message: String?
)

data class CommitterDTO(
    @Expose
    val name: String?,
    @Expose
    val email: String?,
    @Expose
    val date: String?
)