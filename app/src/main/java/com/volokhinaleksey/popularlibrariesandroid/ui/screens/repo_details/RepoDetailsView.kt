package com.volokhinaleksey.popularlibrariesandroid.ui.screens.repo_details

import com.volokhinaleksey.popularlibrariesandroid.ui.base_ui.BaseView
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

/**
 * Interface for interacting with UI elements via the presenter
 */

@AddToEndSingle
interface RepoDetailsView : MvpView, BaseView {

    /**
     * The method for setting the repository name in the UI element.
     */

    fun setRepoName(repoName: String)

    /**
     * The method for setting the repository's forks count in the UI element.
     */

    fun setForksCount(forksCount: String)

    /**
     * The method sets a link to a repository on the Internet in the UI element.
     */

    fun setRepoUrl(repoUrl: String)

}