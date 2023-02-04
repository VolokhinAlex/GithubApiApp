package com.volokhinaleksey.popularlibrariesandroid.navigation

import com.github.terrakok.cicerone.Screen
import com.volokhinaleksey.popularlibrariesandroid.model.GithubRepositoryDTO
import com.volokhinaleksey.popularlibrariesandroid.model.GithubUserDTO
import com.volokhinaleksey.popularlibrariesandroid.ui.screens.repo_details.RepoDetailsFragment
import com.volokhinaleksey.popularlibrariesandroid.ui.screens.user.UserFragment
import com.volokhinaleksey.popularlibrariesandroid.ui.screens.users.UsersFragment

/**
 * Interface for convenient implementation of navigation on different operating systems.
 */

interface IScreens {

    /**
     * The main screen of the application with the user's list [UsersFragment]
     */

    fun usersScreen(): Screen

    /**
     * Details screen about a specific user [UserFragment]
     * @param githubUserDTO - Data class with user information
     */

    fun userDetailScreen(githubUserDTO: GithubUserDTO): Screen

    /**
     * User Repository Information Screen [RepoDetailsFragment]
     * @param githubRepositoryDTO - Data class with repository information
     */

    fun repoDetailsScreen(githubRepositoryDTO: GithubRepositoryDTO): Screen
}