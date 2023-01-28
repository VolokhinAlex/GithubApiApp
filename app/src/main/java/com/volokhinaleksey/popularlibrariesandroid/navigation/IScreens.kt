package com.volokhinaleksey.popularlibrariesandroid.navigation

import com.github.terrakok.cicerone.Screen
import com.volokhinaleksey.popularlibrariesandroid.model.GithubUserDTO
import com.volokhinaleksey.popularlibrariesandroid.model.GithubRepositoryDTO

/**
 * Interface for convenient implementation of navigation on different operating systems.
 */

interface IScreens {
    fun usersScreen(): Screen
    fun userDetailScreen(data: GithubUserDTO): Screen
    fun repoDetailsScreen(data: GithubRepositoryDTO): Screen
}