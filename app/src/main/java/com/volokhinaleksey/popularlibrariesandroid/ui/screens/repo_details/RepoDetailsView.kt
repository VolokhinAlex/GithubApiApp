package com.volokhinaleksey.popularlibrariesandroid.ui.screens.repo_details

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

/**
 * Interface for interacting with UI elements via the presenter
 */

@AddToEndSingle
interface RepoDetailsView : MvpView {

    /**
     * The method for setting the repository name in the UI element.
     */

    fun setRepoName(repoName: String)

    /**
     * The method for setting the repository's forks count in the UI element.
     */

    fun setForkCount(forksCount: String)

    /**
     * The method sets a link to a repository on the Internet in the UI element.
     */

    fun repoUrl(repoUrl: String)

}