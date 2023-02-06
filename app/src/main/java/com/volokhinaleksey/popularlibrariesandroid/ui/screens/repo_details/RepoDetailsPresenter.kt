package com.volokhinaleksey.popularlibrariesandroid.ui.screens.repo_details

import com.github.terrakok.cicerone.Router
import com.volokhinaleksey.popularlibrariesandroid.model.GithubCommitsDTO
import com.volokhinaleksey.popularlibrariesandroid.model.GithubRepositoryDTO
import com.volokhinaleksey.popularlibrariesandroid.repository.GithubRepositoryCommits
import com.volokhinaleksey.popularlibrariesandroid.ui.items.IUserRepoCommitsListPresenter
import com.volokhinaleksey.popularlibrariesandroid.ui.screens.repo_details.adapter.CommitsItemView
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter
import javax.inject.Inject

class RepoDetailsPresenter(
    private val repositoryData: GithubRepositoryDTO?
) : MvpPresenter<RepoDetailsView>() {

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var repository: GithubRepositoryCommits

    @Inject
    lateinit var uiScheduler: Scheduler

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.loadingState()
        viewState.init()
        repoDataLoad()
    }

    val userRepoCommitsListPresenter: IUserRepoCommitsListPresenter = UserRepoCommitsListPresenter()

    private val compositeDisposable = CompositeDisposable()

    class UserRepoCommitsListPresenter : IUserRepoCommitsListPresenter {

        /**
         * List with data
         */

        override val commits = mutableListOf<GithubCommitsDTO>()

        /**
         * Events of clicking on a list item
         */

        override var onItemClickListener: ((CommitsItemView) -> Unit)? = null

        /**
         * Filling the list with data
         */

        override fun bindView(view: CommitsItemView) {
            val commit = commits[view.pos]
            view.setCommitMessage(commit.commit?.message.orEmpty())
            view.setCommitDate(commit.commit?.committer?.date.orEmpty())
            view.setCommitterEmail(commit.commit?.committer?.email.orEmpty())
            view.setCommitterName(commit.commit?.committer?.name.orEmpty())
        }

        /**
         * Getting the list size
         */

        override fun getItemsCount(): Int = commits.size
    }

    /**
     * Method for loading data
     */

    private fun repoDataLoad() {
        repositoryData?.apply {
            viewState.setRepoName(repoName = name.orEmpty())
            viewState.setForkCount(forksCount = forks.toString())
            viewState.repoUrl(repoUrl = htmlUrl.orEmpty())
            loadCommits(repositoryData = this)
        }
    }

    private fun loadCommits(repositoryData: GithubRepositoryDTO) {
        compositeDisposable.add(
            repository.getRepositoryCommits(repositoryData = repositoryData).observeOn(uiScheduler)
                .subscribe({
                    userRepoCommitsListPresenter.commits.clear()
                    userRepoCommitsListPresenter.commits.addAll(it)
                    viewState.updateList()
                    viewState.successState()
                }, {
                    viewState.errorState(message = it.message.orEmpty())
                })
        )
    }


    fun backPressed(): Boolean {
        router.exit()
        return true
    }

}