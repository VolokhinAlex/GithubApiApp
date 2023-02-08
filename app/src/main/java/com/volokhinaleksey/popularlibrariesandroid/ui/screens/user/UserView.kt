package com.volokhinaleksey.popularlibrariesandroid.ui.screens.user

import com.volokhinaleksey.popularlibrariesandroid.model.GithubUserDTO
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

/**
 * Interface for interacting with UI elements via the presenter
 */

@AddToEndSingle
interface UserView : MvpView {

    /**
     * Method for initial initialization of UI elements
     */

    fun init()

    /**
     * Method for updating the list of UI elements
     */

    fun updateList()

    /**
     * Method for setting user data in UI elements
     */

    fun setUserData(githubUser: GithubUserDTO)

    /**
     * Data loading state
     */

    fun loadingState()

    /**
     * The state when the data is loaded
     */

    fun successState()

    /**
     * Error state when loading data
     */

    fun errorState(message: String)
}