package com.volokhinaleksey.popularlibrariesandroid.ui.screens.user

import com.github.terrakok.cicerone.Router
import com.volokhinaleksey.popularlibrariesandroid.model.GithubUserRepo
import com.volokhinaleksey.popularlibrariesandroid.navigation.IScreens
import com.volokhinaleksey.popularlibrariesandroid.repository.GithubUsersRepo
import com.volokhinaleksey.popularlibrariesandroid.ui.items.IUserReposListPresenter
import com.volokhinaleksey.popularlibrariesandroid.ui.screens.user.adapter.RepoItemView
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.MvpPresenter
import timber.log.Timber

private const val SERVER_ERROR = "Server Error"

class UserPresenter(
    private val userRepo: GithubUsersRepo,
    private val uiScheduler: Scheduler,
    private val router: Router,
    private val screens: IScreens
) : MvpPresenter<UserView>() {

    private val compositeDisposable = CompositeDisposable()

    class UserReposListPresenter : IUserReposListPresenter {
        val repos = mutableListOf<GithubUserRepo>()
        override var onItemClickListener: ((RepoItemView) -> Unit)? = null

        override fun bindView(view: RepoItemView) {
            view.setRepoName(repos[view.pos].name.orEmpty())
        }

        override fun getItemsCount(): Int = repos.size
    }

    val userReposListPresenter = UserReposListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        userReposListPresenter.onItemClickListener = {
            router.navigateTo(screens.repoDetailsScreen(userReposListPresenter.repos[it.pos]))
        }
    }

    fun getUserInfoByLogin(userName: String) {
        compositeDisposable.add(
            userRepo.getUserByLogin(userName).observeOn(uiScheduler).subscribe({ data ->
                viewState.setUserData(data)
                data.login?.let { loadData(it) }
            }, {
                Timber.e("$SERVER_ERROR: $it")
            })
        )
    }

    private fun loadData(userName: String) {
        compositeDisposable.add(
            userRepo.getUserRepos(userName).observeOn(uiScheduler).subscribe({ data ->
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