package com.volokhinaleksey.popularlibrariesandroid.navigation

import com.github.terrakok.cicerone.Screen
import com.volokhinaleksey.popularlibrariesandroid.model.GithubUser
import com.volokhinaleksey.popularlibrariesandroid.model.GithubUserRepo

interface IScreens {
    fun usersScreen(): Screen
    fun userDetailScreen(data: GithubUser): Screen
    fun repoDetailsScreen(data: GithubUserRepo): Screen
}