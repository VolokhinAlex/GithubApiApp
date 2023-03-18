package com.volokhinaleksey.popularlibrariesandroid.repository

import com.volokhinaleksey.popularlibrariesandroid.model.GithubRepositoryDTO
import com.volokhinaleksey.popularlibrariesandroid.model.GithubUserDTO
import io.reactivex.rxjava3.core.Single

class FakeGithubUserRepository(
    private val apiHolder: GithubApiHolder
) : GithubUserRepository {

    override fun getUserRepos(user: GithubUserDTO): Single<List<GithubRepositoryDTO>> {
        return apiHolder.apiService.getUserRepos(user.reposUrl.orEmpty())
    }

    override fun getUserByLogin(user: GithubUserDTO): Single<GithubUserDTO> {
        return apiHolder.apiService.getUserData(user.url.orEmpty())
    }

}