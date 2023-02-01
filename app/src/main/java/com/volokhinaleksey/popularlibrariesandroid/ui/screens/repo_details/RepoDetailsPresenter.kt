package com.volokhinaleksey.popularlibrariesandroid.ui.screens.repo_details

import com.github.terrakok.cicerone.Router
import com.volokhinaleksey.popularlibrariesandroid.model.GithubRepositoryDTO
import moxy.MvpPresenter
import javax.inject.Inject

class RepoDetailsPresenter(
    private val repositoryData: GithubRepositoryDTO?
) : MvpPresenter<RepoDetailsView>() {

    @Inject
    lateinit var router: Router

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        repositoryData?.apply {
            name?.let { viewState.setRepoName(it) }
            viewState.setForkCount(forks.toString())
            htmlUrl?.let { viewState.repoUrl(it) }
        }
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

}