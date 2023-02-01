package com.volokhinaleksey.popularlibrariesandroid.di.components

import com.volokhinaleksey.popularlibrariesandroid.di.modules.UsersModule
import com.volokhinaleksey.popularlibrariesandroid.di.scopes.UsersScope
import com.volokhinaleksey.popularlibrariesandroid.ui.screens.users.UsersPresenter
import com.volokhinaleksey.popularlibrariesandroid.ui.screens.users.adapter.UsersAdapter
import dagger.Subcomponent

@UsersScope
@Subcomponent(
    modules = [
        UsersModule::class
    ]
)
interface UsersSubcomponent {

    fun userSubcomponent(): UserSubcomponent
    fun injectUsersPresenter(): UsersPresenter
    fun injectUsersAdapter(): UsersAdapter

}