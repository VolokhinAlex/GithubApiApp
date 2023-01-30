package com.volokhinaleksey.popularlibrariesandroid.ui.screens.user

import com.github.terrakok.cicerone.Router
import com.volokhinaleksey.popularlibrariesandroid.model.GithubRepositoryDTO
import com.volokhinaleksey.popularlibrariesandroid.model.GithubUserDTO
import com.volokhinaleksey.popularlibrariesandroid.navigation.IScreens
import com.volokhinaleksey.popularlibrariesandroid.repository.GithubRepositoriesRepository
import com.volokhinaleksey.popularlibrariesandroid.repository.GithubUsersRepository
import com.volokhinaleksey.popularlibrariesandroid.ui.items.IUserReposListPresenter
import com.volokhinaleksey.popularlibrariesandroid.ui.screens.user.adapter.RepoItemView
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.MvpPresenter
import timber.log.Timber
import javax.inject.Inject

private const val SERVER_ERROR = "Server Error"

class UserPresenter(
    private val githubUser: GithubUserDTO?
) : MvpPresenter<UserView>() {

    @Inject
    lateinit var uiScheduler: Scheduler

    @Inject
    lateinit var userRepo: GithubUsersRepository

    @Inject
    lateinit var repositoryRepo: GithubRepositoriesRepository

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var screens: IScreens

    private val compositeDisposable = CompositeDisposable()

    class UserReposListPresenter : IUserReposListPresenter {
        val repos = mutableListOf<GithubRepositoryDTO>()
        override var onItemClickListener: ((RepoItemView) -> Unit)? = null

        override fun bindView(view: RepoItemView) {
            repos[view.pos].name?.let { view.setRepoName(it) }
        }

        override fun getItemsCount(): Int = repos.size
    }

    val userReposListPresenter = UserReposListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        githubUser?.let {
            getUserInfoByLogin(it)
            loadUserRepositories(it)
        }
        userReposListPresenter.onItemClickListener = {
            router.navigateTo(screens.repoDetailsScreen(userReposListPresenter.repos[it.pos]))
        }
    }

    private fun getUserInfoByLogin(user: GithubUserDTO) {
        compositeDisposable.add(
            userRepo.getUserByLogin(user).observeOn(uiScheduler).subscribe({ data ->
                viewState.setUserData(data)
            }, {
                Timber.e("$SERVER_ERROR: $it")
            })
        )
    }

    private fun loadUserRepositories(user: GithubUserDTO) {
        compositeDisposable.add(
            repositoryRepo.getUserRepos(user).observeOn(uiScheduler).subscribe({ data ->
                userReposListPresenter.repos.clear()
                userReposListPresenter.repos.addAll(data)
                viewState.updateList()
            }, { error ->
                Timber.e("$SERVER_ERROR: $error")
            })
        )
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}