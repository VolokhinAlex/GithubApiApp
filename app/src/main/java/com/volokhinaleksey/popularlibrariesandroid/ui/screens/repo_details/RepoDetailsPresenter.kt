package com.volokhinaleksey.popularlibrariesandroid.ui.screens.repo_details

import com.github.terrakok.cicerone.Router
import com.volokhinaleksey.popularlibrariesandroid.model.GithubCommitsDTO
import com.volokhinaleksey.popularlibrariesandroid.model.GithubRepositoryDTO
import com.volokhinaleksey.popularlibrariesandroid.repository.GithubRepositoryCommits
import com.volokhinaleksey.popularlibrariesandroid.ui.items.IUserRepoCommitsListPresenter
import com.volokhinaleksey.popularlibrariesandroid.ui.screens.repo_details.adapter.CommitsItemView
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.MvpPresenter

class RepoDetailsPresenter @AssistedInject constructor(
    @Assisted("githubRepositoryDTO") private val githubRepositoryDTO: GithubRepositoryDTO?,
    private val router: Router,
    private val repositoryCommits: GithubRepositoryCommits,
    private val uiScheduler: Scheduler
) : MvpPresenter<RepoDetailsView>() {

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
     * Callback after the first presenter init and view binding.
     * If this presenter instance will have to attach more views in the future, this method will not be called.
     */

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.loadingState()
        viewState.init()
        detailsRepositoryDataLoad()
    }

    /**
     * Method for loading data
     */

    private fun detailsRepositoryDataLoad() {
        githubRepositoryDTO?.apply {
            viewState.setRepoName(repoName = name.orEmpty())
            viewState.setForksCount(forksCount = forks.toString())
            viewState.setRepoUrl(repoUrl = htmlUrl.orEmpty())
            repositoryCommitsLoad(repositoryData = this)
        }
    }

    private fun repositoryCommitsLoad(repositoryData: GithubRepositoryDTO) {
        compositeDisposable.add(
            repositoryCommits.getRepositoryCommits(githubRepository = repositoryData)
                .observeOn(uiScheduler)
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

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }

    @AssistedFactory
    interface Factory {
        fun create(@Assisted("githubRepositoryDTO") githubRepositoryDTO: GithubRepositoryDTO?): RepoDetailsPresenter
    }

}