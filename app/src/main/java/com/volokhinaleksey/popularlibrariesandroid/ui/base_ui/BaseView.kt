package com.volokhinaleksey.popularlibrariesandroid.ui.base_ui

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface BaseView : MvpView {

    /**
     * Method for initial initialization of UI elements
     */

    fun init()

    /**
     * Method for updating the list of UI elements
     */

    fun updateList()

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