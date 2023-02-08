package com.volokhinaleksey.popularlibrariesandroid.navigation

import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.volokhinaleksey.popularlibrariesandroid.model.GithubRepositoryDTO
import com.volokhinaleksey.popularlibrariesandroid.model.GithubUserDTO
import com.volokhinaleksey.popularlibrariesandroid.ui.screens.repo_details.RepoDetailsFragment
import com.volokhinaleksey.popularlibrariesandroid.ui.screens.user.UserFragment
import com.volokhinaleksey.popularlibrariesandroid.ui.screens.users.UsersFragment

/**
 * Class navigation between screens for the application
 */

class NavigationScreens : IScreens {

    /**
     * The main screen of the application with the user's list [UsersFragment]
     */

    override fun usersScreen(): Screen = FragmentScreen { UsersFragment.newInstance() }

    /**
     * Details screen about a specific user [UserFragment]
     * @param githubUserDTO - Data class with user information
     */

    override fun userDetailScreen(githubUserDTO: GithubUserDTO): Screen =
        FragmentScreen { UserFragment.newInstance(arg = githubUserDTO) }

    /**
     * User Repository Information Screen [RepoDetailsFragment]
     * @param githubRepositoryDTO - Data class with repository information
     */

    override fun repoDetailsScreen(githubRepositoryDTO: GithubRepositoryDTO): Screen =
        FragmentScreen { RepoDetailsFragment.newInstance(arg = githubRepositoryDTO) }
}