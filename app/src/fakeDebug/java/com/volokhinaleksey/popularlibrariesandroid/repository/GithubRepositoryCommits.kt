package com.volokhinaleksey.popularlibrariesandroid.repository

import com.volokhinaleksey.popularlibrariesandroid.model.CommitDTO
import com.volokhinaleksey.popularlibrariesandroid.model.CommitterDTO
import com.volokhinaleksey.popularlibrariesandroid.model.GithubCommitsDTO
import com.volokhinaleksey.popularlibrariesandroid.model.GithubRepositoryDTO
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

interface GithubRepositoryCommits {

    fun getRepositoryCommits(githubRepository: GithubRepositoryDTO): Single<List<GithubCommitsDTO>>

}

class GithubRepositoryCommitsImpl @Inject constructor() : GithubRepositoryCommits {

    override fun getRepositoryCommits(githubRepository: GithubRepositoryDTO): Single<List<GithubCommitsDTO>> {
        val commits = mutableListOf<GithubCommitsDTO>()
        for (it in 0..50) {
            commits.add(
                GithubCommitsDTO(
                    sha = "$it",
                    commit = CommitDTO(
                        committer = CommitterDTO(
                            name = "Name: $it",
                            email = "Email: $it",
                            date = "Date: $it"
                        ),
                        message = "Message: $it"
                    )
                )
            )
        }
        return Single.just(commits.toList()).subscribeOn(Schedulers.io())
    }

}