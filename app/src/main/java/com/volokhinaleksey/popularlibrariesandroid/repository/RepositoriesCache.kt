package com.volokhinaleksey.popularlibrariesandroid.repository

import com.volokhinaleksey.popularlibrariesandroid.model.GithubRepositoryDTO
import com.volokhinaleksey.popularlibrariesandroid.model.GithubUserDTO
import com.volokhinaleksey.popularlibrariesandroid.room.GithubRoomDatabase
import com.volokhinaleksey.popularlibrariesandroid.utils.convertGithubRepositoryToRoomGithubUserRepo
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

interface RepositoriesCache {
    fun cacheUserRepositoriesToDatabase(
        repositories: List<GithubRepositoryDTO>,
        userDTO: GithubUserDTO
    ): Single<List<GithubRepositoryDTO>>
}

/**
 * Implementation of an interface for caching data to a local sqlite database under Room management
 *
 * @param localDatabase - A local database instance that is automatically injected using dagger
 */

class RoomGithubRepositoriesCacheImpl
@Inject constructor(private val localDatabase: GithubRoomDatabase) : RepositoriesCache {

    /**
     * The method gets the user login from the GithubUserDTO data class performs a search in the
     * local database, if there is such a user, then the list of repositories is written to the database.
     *
     * @param repositories - A class with user's repositories
     * @param userDTO - A class with user data
     *
     * @return - The method returns a single RxJava object, in which the list of user's repositories is wrapped.
     */

    override fun cacheUserRepositoriesToDatabase(
        repositories: List<GithubRepositoryDTO>,
        userDTO: GithubUserDTO
    ): Single<List<GithubRepositoryDTO>> = Single.fromCallable {
        userDTO.login?.let {
            val roomUser = localDatabase.userDao.findByLogin(it)
            val roomRepos = repositories.map { user ->
                convertGithubRepositoryToRoomGithubUserRepo(user, roomUser?.id)
            }
            localDatabase.repositoryDao.upsert(roomRepos)
        }
        repositories
    }

}