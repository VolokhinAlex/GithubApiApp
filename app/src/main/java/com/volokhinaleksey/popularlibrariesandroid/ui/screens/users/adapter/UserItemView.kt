package com.volokhinaleksey.popularlibrariesandroid.ui.screens.users.adapter

import com.volokhinaleksey.popularlibrariesandroid.ui.items.ItemView

interface UserItemView : ItemView {
    fun setLogin(login: String)
    fun setUserAvatar(url: String)
}