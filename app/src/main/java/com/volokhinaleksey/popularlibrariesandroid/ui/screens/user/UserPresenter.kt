package com.volokhinaleksey.popularlibrariesandroid.ui.screens.user

import com.github.terrakok.cicerone.Router
import com.volokhinaleksey.popularlibrariesandroid.di.scopes.UserScopeContainer
import com.volokhinaleksey.popularlibrariesandroid.model.GithubRepositoryDTO
import com.volokhinaleksey.popularlibrariesandroid.model.GithubUserDTO
import com.volokhinaleksey.popularlibrariesandroid.navigation.IScreens
import com.volokhinaleksey.popularlibrariesandroid.repository.GithubUserRepository
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
    lateinit var userRepo: GithubUserRepository

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var screens: IScreens

    val userReposListPresenter: IUserReposListPresenter = UserReposListPresenter()

    @Inject
    lateinit var userScopeContainer: UserScopeContainer

    /**
     * Variable for collecting disposable objects into one
     */

    private val compositeDisposable = CompositeDisposable()

    class UserReposListPresenter : IUserReposListPresenter {

        /**
         * List with data
         */

        override val repos = mutableListOf<GithubRepositoryDTO>()

        /**
         * Events of clicking on a list item
         */

        override var onItemClickListener: ((RepoItemView) -> Unit)? = null

        /**
         * Filling the list with data
         */

        override fun bindView(view: RepoItemView) {
            repos[view.pos].name?.let { view.setRepoName(it) }
        }

        /**
         * Getting the list size
         */

        override fun getItemsCount(): Int = repos.size
    }

    /**
     * Callback after the first presenter init and view binding.
     * If this presenter instance will have to attach more views in the future, this method will not be called.
     */

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

    /**
     * Method for getting user data by login from the repository
     * @param user - A user data.
     */

    private fun getUserInfoByLogin(user: GithubUserDTO) {
        compositeDisposable.add(
            userRepo.getUserByLogin(user).observeOn(uiScheduler).subscribe({ data ->
                viewState.setUserData(data)
            }, {
                Timber.e("$SERVER_ERROR: $it")
            })
        )
    }

    /**
     * Method for getting a list of user's repositories from the repository
     * @param user - A user data.
     */

    private fun loadUserRepositories(user: GithubUserDTO) {
        compositeDisposable.add(
            userRepo.getUserRepos(user).observeOn(uiScheduler).subscribe({ data ->
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

    override fun onDestroy() {
        super.onDestroy()
        userScopeContainer.releaseUserScope()
    }
}