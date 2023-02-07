package com.volokhinaleksey.popularlibrariesandroid.ui.screens.users.adapter

import com.volokhinaleksey.popularlibrariesandroid.ui.items.ItemView

/**
 * Interface for interacting with UI elements of the list
 */

interface UserItemView : ItemView {
    fun setLogin(login: String)
    fun setUserAvatar(url: String)
}