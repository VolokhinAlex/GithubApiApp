package com.volokhinaleksey.popularlibrariesandroid.repository

import com.volokhinaleksey.popularlibrariesandroid.model.GithubUserDTO
import io.reactivex.rxjava3.core.Single

class FakeGithubUsersRepository(
    private val apiHolder: GithubApiHolder
) : GithubUsersRepository {

    override fun getUsers(): Single<List<GithubUserDTO>> {
        return apiHolder.apiService.getUsersData()
    }

}