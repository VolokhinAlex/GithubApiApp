package com.volokhinaleksey.popularlibrariesandroid.repository

import com.volokhinaleksey.popularlibrariesandroid.model.GithubCommitsDTO
import com.volokhinaleksey.popularlibrariesandroid.model.GithubRepositoryDTO
import io.reactivex.rxjava3.core.Single

class FakeGithubRepositoryCommits(
    private val apiHolder: GithubApiHolder
) : GithubRepositoryCommits {
    override fun getRepositoryCommits(githubRepository: GithubRepositoryDTO): Single<List<GithubCommitsDTO>> {
        return apiHolder.apiService.getRepoCommits(githubRepository.commitsUrl.orEmpty())
    }
}