package com.volokhinaleksey.popularlibrariesandroid.repository

import com.volokhinaleksey.popularlibrariesandroid.model.GithubRepositoryDTO
import com.volokhinaleksey.popularlibrariesandroid.model.GithubUserDTO
import com.volokhinaleksey.popularlibrariesandroid.room.GithubRoomDatabase
import com.volokhinaleksey.popularlibrariesandroid.utils.mapToRoomGithubUserRepo
import com.volokhinaleksey.popularlibrariesandroid.utils.mapToRoomGithubUser
import com.volokhinaleksey.popularlibrariesandroid.utils.mapToGithubRepository
import com.volokhinaleksey.popularlibrariesandroid.utils.mapToGithubUser
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

interface UserCache {

    /**
     * The method gets the user login from the GithubUserDTO data class performs a search in the
     * local database, if there is such a user, then the list of repositories is written to the database.
     *
     * @param repositories - A class with user's repositories
     * @param userDTO - A class with user data
     *
     * @return - The method returns a single RxJava object, in which the list of user's repositories is wrapped.
     */

    fun cacheUserRepositoriesToDatabase(
        repositories: List<GithubRepositoryDTO>,
        userDTO: GithubUserDTO
    ): Single<List<GithubRepositoryDTO>>

    /**
     * A method for retrieving data from a local database if there is no internet connection on the user's device.
     *
     * @param user - A class with the user data
     *
     * @return - The method returns a single RxJava object, in which the list of user's repositories is wrapped.
     */

    fun getUserRepositoriesFromDatabase(user: GithubUserDTO): Single<List<GithubRepositoryDTO>>

    /**
     * The method writes a user info to the database
     *
     * @param githubUser - A class with the user data
     *
     * @return - The method returns a single RxJava object, in which the user info is wrapped.
     */

    fun cacheUserToDatabase(
        githubUser: GithubUserDTO,
    ): Single<GithubUserDTO>

    /**
     * A method for retrieving data from a local database if there is no internet connection on the user's device.
     *
     * @param user - A class with the user data
     *
     * @return - The method returns a single RxJava object, in which the user data is wrapped.
     */

    fun getUserDataFromDatabase(user: GithubUserDTO): Single<GithubUserDTO>
}

/**
 * Implementation of an interface for caching data to a local sqlite database under Room management
 *
 * @param localDatabase - A local database instance that is automatically injected using dagger
 */

class RoomUserCacheImpl
@Inject constructor(private val localDatabase: GithubRoomDatabase) : UserCache {

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
            val roomUser = localDatabase.userDao.getUserByLogin(it)
            val roomRepos = repositories.map { user ->
                mapToRoomGithubUserRepo(
                    githubRepo = user,
                    userId = roomUser?.id
                )
            }
            localDatabase.repositoryDao.upsert(roomRepos)
        }
        repositories
    }

    /**
     * A method for retrieving data from a local database if there is no internet connection on the user's device.
     *
     * @param user - A class with the user data
     *
     * @return - The method returns a single RxJava object, in which the list of user's repositories is wrapped.
     */

    override fun getUserRepositoriesFromDatabase(user: GithubUserDTO): Single<List<GithubRepositoryDTO>> =
        Single.fromCallable {
            val roomUser = user.login?.let { localDatabase.userDao.getUserByLogin(it) }
            localDatabase.repositoryDao.getRepositoriesByUserId(userId = roomUser?.id.toString())
                .map { roomGithubUser ->
                    mapToGithubRepository(roomGithubRepo = roomGithubUser)
                }
        }


    /**
     * The method writes a user info to the database
     *
     * @param githubUser - A class with the user data
     *
     * @return - The method returns a single RxJava object, in which the user info is wrapped.
     */

    override fun cacheUserToDatabase(
        githubUser: GithubUserDTO,
    ): Single<GithubUserDTO> = Single.fromCallable {
        val roomUsers = mapToRoomGithubUser(githubUser = githubUser)
        localDatabase.userDao.upsert(roomUsers)
        githubUser
    }

    /**
     * A method for retrieving data from a local database if there is no internet connection on the user's device.
     *
     * @param user - A class with the user data
     *
     * @return - The method returns a single RxJava object, in which the user data is wrapped.
     */

    override fun getUserDataFromDatabase(user: GithubUserDTO): Single<GithubUserDTO> =
        Single.fromCallable {
            localDatabase.userDao.getUserByLogin(user.login.orEmpty())
                ?.let { mapToGithubUser(roomGithubUser = it) }
        }

}