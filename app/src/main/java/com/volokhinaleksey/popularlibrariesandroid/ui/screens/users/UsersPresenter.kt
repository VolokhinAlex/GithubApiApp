package com.volokhinaleksey.popularlibrariesandroid.ui.screens.users

import com.github.terrakok.cicerone.Router
import com.volokhinaleksey.popularlibrariesandroid.model.GithubUserDTO
import com.volokhinaleksey.popularlibrariesandroid.navigation.IScreens
import com.volokhinaleksey.popularlibrariesandroid.repository.GithubUsersRepository
import com.volokhinaleksey.popularlibrariesandroid.ui.items.IUserListPresenter
import com.volokhinaleksey.popularlibrariesandroid.ui.screens.users.adapter.UserItemView
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.MvpPresenter
import timber.log.Timber
import javax.inject.Inject

private const val SERVER_ERROR = "Server Error"

class UsersPresenter @Inject constructor(
    private val usersRepository: GithubUsersRepository,
    private val router: Router,
    private val screens: IScreens,
    private val uiScheduler: Scheduler,
    private val usersListPresenter: IUserListPresenter
) : MvpPresenter<UsersView>() {

    private val compositeDisposable = CompositeDisposable()

    class UsersListPresenter : IUserListPresenter {
        override val users = mutableListOf<GithubUserDTO>()
        override var onItemClickListener: ((UserItemView) -> Unit)? = null

        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            user.login?.let { view.setLogin(it) }
            user.avatarUrl?.let { view.setAvatar(it) }
        }

        override fun getItemsCount(): Int = users.size
    }

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