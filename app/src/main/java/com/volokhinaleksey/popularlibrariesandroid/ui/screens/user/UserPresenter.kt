package com.volokhinaleksey.popularlibrariesandroid.ui.screens.user

import com.github.terrakok.cicerone.Router
import com.volokhinaleksey.popularlibrariesandroid.di.scopes.UserScopeContainer
import com.volokhinaleksey.popularlibrariesandroid.model.GithubRepositoryDTO
import com.volokhinaleksey.popularlibrariesandroid.model.GithubUserDTO
import com.volokhinaleksey.popularlibrariesandroid.navigation.IScreens
import com.volokhinaleksey.popularlibrariesandroid.repository.GithubUserRepository
import com.volokhinaleksey.popularlibrariesandroid.ui.items.IUserReposListPresenter
import com.volokhinaleksey.popularlibrariesandroid.ui.screens.user.adapter.RepoItemView
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.MvpPresenter
import timber.log.Timber

private const val SERVER_ERROR = "Server Error"

class UserPresenter @AssistedInject constructor(
    @Assisted("githubUser") private val githubUser: GithubUserDTO?,
    private val uiScheduler: Scheduler,
    private val userRepository: GithubUserRepository,
    private val router: Router,
    private val screens: IScreens,
    private val userScopeContainer: UserScopeContainer
) : MvpPresenter<UserView>() {

    val userReposListPresenter: IUserReposListPresenter = UserReposListPresenter()

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
            view.setRepoName(repos[view.pos].name.orEmpty())
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
        viewState.loadingState()
        viewState.init()
        githubUser?.let { loadUserDetailsData(it) }
        githubUser?.let { loadUserRepositories(user = it) }
        userReposListPresenter.onItemClickListener = {
            router.navigateTo(screens.repoDetailsScreen(userReposListPresenter.repos[it.pos]))
        }
    }

    /**
     * Method for getting user data by login from the repository
     * @param user - A user data.
     */

    private fun loadUserDetailsData(user: GithubUserDTO) {
        compositeDisposable.add(
            userRepository.getUserByLogin(user = user).observeOn(uiScheduler).subscribe({ data ->
                viewState.setUserData(githubUser = data)
            }, {
                viewState.errorState(message = it.message.orEmpty())
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
            userRepository.getUserRepos(user = user).observeOn(uiScheduler).subscribe({ data ->
                userReposListPresenter.repos.clear()
                userReposListPresenter.repos.addAll(data)
                viewState.successState()
                viewState.updateList()
            }, { error ->
                viewState.errorState(message = error.message.orEmpty())
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
        compositeDisposable.clear()
    }

    @AssistedFactory
    interface Factory {
        fun create(@Assisted("githubUser") githubUser: GithubUserDTO?): UserPresenter
    }
}