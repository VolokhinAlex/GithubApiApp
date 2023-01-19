package com.volokhinaleksey.popularlibrariesandroid.navigation

import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.volokhinaleksey.popularlibrariesandroid.model.GithubUser
import com.volokhinaleksey.popularlibrariesandroid.ui.user_details.UserFragment
import com.volokhinaleksey.popularlibrariesandroid.ui.users.UsersFragment

class NavigationScreens(private val data: GithubUser? = null) : IScreens {
    override fun usersScreen(): Screen = FragmentScreen { UsersFragment() }
    override fun userDetailScreen(): Screen = FragmentScreen { UserFragment.newInstance(data) }
}