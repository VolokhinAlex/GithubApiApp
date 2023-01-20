package com.volokhinaleksey.popularlibrariesandroid.presentation

import com.volokhinaleksey.popularlibrariesandroid.ui.IItemView
import com.volokhinaleksey.popularlibrariesandroid.ui.UserItemView

interface IListPresenter<V: IItemView> {
    var onItemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getItemsCount(): Int
}
interface IUserListPresenter : IListPresenter<UserItemView>
