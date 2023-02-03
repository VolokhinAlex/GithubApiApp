package com.volokhinaleksey.popularlibrariesandroid.navigation

import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.volokhinaleksey.popularlibrariesandroid.model.GithubUser
import com.volokhinaleksey.popularlibrariesandroid.model.GithubUserRepo
import com.volokhinaleksey.popularlibrariesandroid.ui.screens.user.UserFragment
import com.volokhinaleksey.popularlibrariesandroid.ui.screens.repo_details.RepoDetailsFragment
import com.volokhinaleksey.popularlibrariesandroid.ui.screens.users.UsersFragment

/**
 * Class navigation between screens for the application
 */

class NavigationScreens : IScreens {
    override fun usersScreen(): Screen = FragmentScreen { UsersFragment() }
    override fun userDetailScreen(data: GithubUser): Screen =
        FragmentScreen { UserFragment.newInstance(data) }

    override fun repoDetailsScreen(data: GithubUserRepo): Screen =
        FragmentScreen { RepoDetailsFragment.newInstance(data) }
}