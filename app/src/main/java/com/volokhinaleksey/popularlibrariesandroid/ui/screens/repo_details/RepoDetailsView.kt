package com.volokhinaleksey.popularlibrariesandroid.ui.screens.repo_details

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

/**
 * Interface for interacting with UI elements via the presenter
 */

@AddToEndSingle
interface RepoDetailsView : MvpView {

    fun setRepoName(repoName: String)
    fun setForkCount(forksCount: String)
    fun repoUrl(repoUrl: String)

}