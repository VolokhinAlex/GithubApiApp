package com.volokhinaleksey.popularlibrariesandroid.di.components

import com.volokhinaleksey.popularlibrariesandroid.di.modules.UserModule
import com.volokhinaleksey.popularlibrariesandroid.di.scopes.UserScope
import com.volokhinaleksey.popularlibrariesandroid.ui.screens.repo_details.RepoDetailsPresenter
import com.volokhinaleksey.popularlibrariesandroid.ui.screens.user.UserFragment
import com.volokhinaleksey.popularlibrariesandroid.ui.screens.user.UserPresenter
import dagger.Subcomponent

@UserScope
@Subcomponent(
    modules = [
        UserModule::class
    ]
)
interface UserSubcomponent {

    val userPresenterFactory: UserPresenter.Factory
    val repoDetailsPresenterFactory: RepoDetailsPresenter.Factory

    fun injectUserPresenter(userPresenter: UserPresenter)
    fun injectUserFragment(userFragment: UserFragment)

}