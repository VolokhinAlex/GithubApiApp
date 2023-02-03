package com.volokhinaleksey.popularlibrariesandroid.ui.screens.users

import com.github.terrakok.cicerone.Router
import com.volokhinaleksey.popularlibrariesandroid.model.GithubUser
import com.volokhinaleksey.popularlibrariesandroid.navigation.IScreens
import com.volokhinaleksey.popularlibrariesandroid.repository.GithubUsersRepo
import com.volokhinaleksey.popularlibrariesandroid.ui.items.IUserListPresenter
import com.volokhinaleksey.popularlibrariesandroid.ui.screens.users.adapter.UserItemView
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.MvpPresenter
import timber.log.Timber

private const val SERVER_ERROR = "Server Error"

class UsersPresenter(
    private val usersRepository: GithubUsersRepo,
    private val router: Router,
    private val uiScheduler: Scheduler,
    private val screens: IScreens
) : MvpPresenter<UsersView>() {

    private val compositeDisposable = CompositeDisposable()

    class UsersListPresenter : IUserListPresenter {
        val users = mutableListOf<GithubUser>()
        override var onItemClickListener: ((UserItemView) -> Unit)? = null

        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            user.login?.let { view.setLogin(it) }
            user.avatarUrl?.let { view.setAvatar(it) }
        }

        override fun getItemsCount(): Int = users.size

    }

    val usersListPresenter = UsersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()
        usersListPresenter.onItemClickListener = {
            router.navigateTo(screens.userDetailScreen(usersListPresenter.users[it.pos]))
        }
    }

    private fun loadData() {
        compositeDisposable.add(
            usersRepository.getUsers()
                .observeOn(uiScheduler)
                .subscribe({ user ->
                    usersListPresenter.users.clear()
                    usersListPresenter.users.addAll(user)
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