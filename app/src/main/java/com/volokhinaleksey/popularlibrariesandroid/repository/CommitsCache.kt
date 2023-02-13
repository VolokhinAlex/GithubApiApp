package com.volokhinaleksey.popularlibrariesandroid.repository

import com.volokhinaleksey.popularlibrariesandroid.model.GithubCommitsDTO
import com.volokhinaleksey.popularlibrariesandroid.model.GithubRepositoryDTO
import com.volokhinaleksey.popularlibrariesandroid.room.GithubRoomDatabase
import com.volokhinaleksey.popularlibrariesandroid.utils.mapToGithubCommitsDTO
import com.volokhinaleksey.popularlibrariesandroid.utils.mapToRoomGithubRepoCommits
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

interface CommitsCache {

    fun cacheRepoCommitsToDatabase(
        commits: List<GithubCommitsDTO>,
        repository: GithubRepositoryDTO
    ): Single<List<GithubCommitsDTO>>

    fun getCommitsDataFromDatabase(repoId: Long): Single<List<GithubCommitsDTO>>

}

class GithubRepoCommits @Inject constructor(
    private val localDatabase: GithubRoomDatabase
) : CommitsCache {

    override fun cacheRepoCommitsToDatabase(
        commits: List<GithubCommitsDTO>,
        repository: GithubRepositoryDTO
    ): Single<List<GithubCommitsDTO>> =
        Single.fromCallable {
            repository.id?.let { repoId ->
                val roomCommits = commits.map { commit ->
                    mapToRoomGithubRepoCommits(
                        githubRepoCommits = commit,
                        repoId = repoId
                    )
                }
                localDatabase.commitsDao.upsert(commits = roomCommits)
            }
            commits
        }

    override fun getCommitsDataFromDatabase(repoId: Long): Single<List<GithubCommitsDTO>> =
        Single.fromCallable {
            localDatabase.commitsDao.getCommitsByRepoId(
                repoId
            ).map {
                mapToGithubCommitsDTO(githubRepoCommits = it)
            }
        }

}