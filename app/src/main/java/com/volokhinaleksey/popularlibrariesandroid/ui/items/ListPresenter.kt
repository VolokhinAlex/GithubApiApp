package com.volokhinaleksey.popularlibrariesandroid.ui.items

import com.volokhinaleksey.popularlibrariesandroid.model.GithubRepositoryDTO
import com.volokhinaleksey.popularlibrariesandroid.model.GithubUserDTO
import com.volokhinaleksey.popularlibrariesandroid.ui.screens.user.adapter.RepoItemView
import com.volokhinaleksey.popularlibrariesandroid.ui.screens.users.adapter.UserItemView

interface IListPresenter<V : ItemView> {
    var onItemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getItemsCount(): Int
}

interface IUserListPresenter : IListPresenter<UserItemView> {
    val users: MutableList<GithubUserDTO>
}


interface IUserReposListPresenter : IListPresenter<RepoItemView> {
    val repos: MutableList<GithubRepositoryDTO>
}

