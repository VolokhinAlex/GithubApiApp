package com.volokhinaleksey.popularlibrariesandroid.ui.screens.user

import com.volokhinaleksey.popularlibrariesandroid.model.GithubUserDTO
import com.volokhinaleksey.popularlibrariesandroid.ui.base_ui.BaseView
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

/**
 * Interface for interacting with UI elements via the presenter
 */

@AddToEndSingle
interface UserView : MvpView, BaseView {

    /**
     * Method for setting user data in UI elements
     */

    fun setUserData(githubUser: GithubUserDTO)

}