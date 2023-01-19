package com.volokhinaleksey.popularlibrariesandroid.ui.users

import android.os.Bundle
import com.github.terrakok.cicerone.Router
import com.volokhinaleksey.popularlibrariesandroid.ui.user_details.UserFragment.Companion.ARG_USER_DATA
import com.volokhinaleksey.popularlibrariesandroid.model.GithubUser
import com.volokhinaleksey.popularlibrariesandroid.navigation.NavigationScreens
import com.volokhinaleksey.popularlibrariesandroid.presenter.IUserListPresenter
import com.volokhinaleksey.popularlibrariesandroid.repository.GithubUsersRepo
import com.volokhinaleksey.popularlibrariesandroid.ui.UserItemView
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
                NavigationScreens(usersRepository.getUsers().get(it.pos)).userDetailScreen()
            )
        }
    }

    private fun loadData() {
        val usersList = usersRepository.getUsers()
        usersListPresenter.users.addAll(usersList)
        viewState.updateList()
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}