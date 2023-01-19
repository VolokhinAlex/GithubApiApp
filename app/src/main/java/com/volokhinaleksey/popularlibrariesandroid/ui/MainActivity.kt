package com.volokhinaleksey.popularlibrariesandroid.ui

import android.os.Bundle
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.volokhinaleksey.popularlibrariesandroid.R
import com.volokhinaleksey.popularlibrariesandroid.app.App
import com.volokhinaleksey.popularlibrariesandroid.databinding.ActivityMainBinding
import com.volokhinaleksey.popularlibrariesandroid.navigation.BackButtonListener
import com.volokhinaleksey.popularlibrariesandroid.navigation.NavigationScreens
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter

class MainActivity : MvpAppCompatActivity(), MainView {
    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding get() = _binding!!

    private val presenter by moxyPresenter {
        MainPresenter(
            App.appInstance.router,
            NavigationScreens()
        )
    }
    private val navigator = AppNavigator(this, R.id.screens_container)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        App.appInstance.navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        App.appInstance.navigatorHolder.removeNavigator()
    }

    override fun onBackPressed() {
        supportFragmentManager.fragments.forEach {
            if (it is BackButtonListener && it.backPressed()) {
                return
            }
        }
        presenter.backClicked()
    }
}