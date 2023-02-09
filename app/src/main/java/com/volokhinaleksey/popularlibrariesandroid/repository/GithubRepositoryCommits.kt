package com.volokhinaleksey.popularlibrariesandroid.repository

import com.volokhinaleksey.popularlibrariesandroid.model.GithubCommitsDTO
import com.volokhinaleksey.popularlibrariesandroid.model.GithubRepositoryDTO
import com.volokhinaleksey.popularlibrariesandroid.utils.NetworkStatus
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

interface GithubRepositoryCommits {

    fun getRepositoryCommits(repositoryData: GithubRepositoryDTO): Single<List<GithubCommitsDTO>>

}

class GithubRepositoryCommitsImpl @Inject constructor(
    private val remoteApiSource: ApiHolder,
    private val networkStatus: NetworkStatus,
    private val repoCache: CommitsCache
) : GithubRepositoryCommits {

    override fun getRepositoryCommits(repositoryData: GithubRepositoryDTO): Single<List<GithubCommitsDTO>> =
        networkStatus.isNetworkAvailableSingle().flatMap { isAvailable ->
            if (isAvailable) {
                repositoryData.commitsUrl?.let {
                    remoteApiSource.apiService.getUserReposCommits(it.substring(0, it.indexOf("{")))
                        .flatMap { commits ->
                            repoCache.cacheRepoCommitToDatabase(
                                commits = commits,
                                repository = repositoryData
                            )
                        }
                } ?: error("")
            } else {
                repoCache.getCommitsDataFromDatabase(repositoryData.id ?: 0)
            }
        }.subscribeOn(Schedulers.io())

}