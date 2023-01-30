package com.volokhinaleksey.popularlibrariesandroid.ui.screens.repo_details

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import javax.inject.Inject

class RepoDetailsPresenter @Inject constructor(private val router: Router) :
    MvpPresenter<RepoDetailsView>() {

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

}