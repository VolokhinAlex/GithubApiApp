package com.volokhinaleksey.popularlibrariesandroid.ui.activity

import com.github.terrakok.cicerone.Router
import com.volokhinaleksey.popularlibrariesandroid.navigation.IScreens
import moxy.MvpPresenter
import javax.inject.Inject

class MainPresenter @Inject constructor(private val router: Router, private val screens: IScreens) :
    MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(screens.usersScreen())
    }

    fun backClicked() {
        router.exit()
    }
}