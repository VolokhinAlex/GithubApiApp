package com.volokhinaleksey.popularlibrariesandroid.ui.items

import com.volokhinaleksey.popularlibrariesandroid.model.GithubRepositoryDTO
import com.volokhinaleksey.popularlibrariesandroid.model.GithubUserDTO
import com.volokhinaleksey.popularlibrariesandroid.ui.screens.user.adapter.RepoItemView
import com.volokhinaleksey.popularlibrariesandroid.ui.screens.users.adapter.UserItemView

/**
 * Interface with common methods for any Recycler View to work with it via presenter
 */

interface IListPresenter<V : ItemView> {
    var onItemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getItemsCount(): Int
}

/**
 * Interface for working with the list of users in the presenter
 */

interface IUserListPresenter : IListPresenter<UserItemView> {
    val users: MutableList<GithubUserDTO>
}

/**
 * Interface for working with the list of user's repositories in the presenter
 */

interface IUserReposListPresenter : IListPresenter<RepoItemView> {
    val repos: MutableList<GithubRepositoryDTO>
}

