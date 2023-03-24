package com.volokhinaleksey.popularlibrariesandroid.repository

import com.volokhinaleksey.popularlibrariesandroid.model.GithubCommitsDTO
import com.volokhinaleksey.popularlibrariesandroid.model.GithubRepositoryDTO
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

interface GithubRepositoryCommits {

    fun getRepositoryCommits(githubRepository: GithubRepositoryDTO): Single<List<GithubCommitsDTO>>

}

class GithubRepositoryCommitsImpl @Inject constructor() : GithubRepositoryCommits {

    override fun getRepositoryCommits(githubRepository: GithubRepositoryDTO): Single<List<GithubCommitsDTO>> =
        Single.just<List<GithubCommitsDTO>>(listOf()).subscribeOn(Schedulers.io())

}