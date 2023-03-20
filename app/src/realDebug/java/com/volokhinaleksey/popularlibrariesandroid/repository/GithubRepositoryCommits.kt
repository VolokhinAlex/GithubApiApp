package com.volokhinaleksey.popularlibrariesandroid.repository

import com.volokhinaleksey.popularlibrariesandroid.model.GithubCommitsDTO
import com.volokhinaleksey.popularlibrariesandroid.model.GithubRepositoryDTO
import com.volokhinaleksey.popularlibrariesandroid.utils.NetworkStatus
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

interface GithubRepositoryCommits {

    fun getRepositoryCommits(githubRepository: GithubRepositoryDTO): Single<List<GithubCommitsDTO>>

}

class GithubRepositoryCommitsImpl @Inject constructor(
    private val remoteApiSource: ApiHolder,
    private val networkStatus: NetworkStatus,
    private val commitsCache: CommitsCache
) : GithubRepositoryCommits {

    override fun getRepositoryCommits(githubRepository: GithubRepositoryDTO): Single<List<GithubCommitsDTO>> =
        networkStatus.isNetworkAvailableSingle().flatMap { isAvailable ->
            if (isAvailable) {
                githubRepository.commitsUrl?.let {
                    remoteApiSource.apiService.getRepoCommits(it.substring(0, it.indexOf("{")))
                        .flatMap { commits ->
                            commitsCache.cacheRepoCommitsToDatabase(
                                commits = commits,
                                repository = githubRepository
                            )
                        }
                } ?: error("The repo has not commits yet")
            } else {
                commitsCache.getCommitsDataFromDatabase(githubRepository.id ?: 0)
            }
        }.subscribeOn(Schedulers.io())

}