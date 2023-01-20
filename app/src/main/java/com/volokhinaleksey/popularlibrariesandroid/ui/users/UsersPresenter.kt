package com.volokhinaleksey.popularlibrariesandroid.ui.users

import android.util.Log
import com.github.terrakok.cicerone.Router
import com.volokhinaleksey.popularlibrariesandroid.model.GithubUser
import com.volokhinaleksey.popularlibrariesandroid.navigation.NavigationScreens
import com.volokhinaleksey.popularlibrariesandroid.presentation.IUserListPresenter
import com.volokhinaleksey.popularlibrariesandroid.repository.GithubUsersRepo
import com.volokhinaleksey.popularlibrariesandroid.ui.UserItemView
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import moxy.MvpPresenter

class UsersPresenter(
    private val usersRepository: GithubUsersRepo,
    private val router: Router
) : MvpPresenter<UsersView>() {

    class UsersListPresenter : IUserListPresenter {
        val users = mutableListOf<GithubUser>()
        override var onItemClickListener: ((UserItemView) -> Unit)? = null

        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            view.setLogin(user.login)
        }

        override fun getItemsCount(): Int = users.size

    }

    val usersListPresenter = UsersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()
        usersListPresenter.onItemClickListener = {
            router.navigateTo(
                NavigationScreens(usersListPresenter.users[it.pos]).userDetailScreen()
            )
        }
    }

    private fun loadData() {
        val usersObserver = object : Observer<GithubUser> {
            override fun onSubscribe(d: Disposable) {
                Log.e("TAG_DEBUG", "onSubscribe")
            }

            override fun onError(e: Throwable) {
                Log.e("TAG_DEBUG", "onError")
            }

            override fun onComplete() {
                Log.e("TAG_DEBUG", "onComplete")
            }

            override fun onNext(user: GithubUser) {
                usersListPresenter.users.add(user)
                viewState.updateList()
            }
        }
        usersRepository.getUsers().subscribe(usersObserver)
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}