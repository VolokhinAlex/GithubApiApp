package com.volokhinaleksey.popularlibrariesandroid.repository

import com.volokhinaleksey.popularlibrariesandroid.model.GithubRepositoryDTO
import com.volokhinaleksey.popularlibrariesandroid.model.GithubUserDTO
import com.volokhinaleksey.popularlibrariesandroid.room.GithubRoomDatabase
import com.volokhinaleksey.popularlibrariesandroid.utils.convertGithubRepositoryToRoomGithubUserRepo
import com.volokhinaleksey.popularlibrariesandroid.utils.convertGithubUserToRoomGithubUser
import com.volokhinaleksey.popularlibrariesandroid.utils.convertRoomGithubUserRepoToGithubRepository
import com.volokhinaleksey.popularlibrariesandroid.utils.convertRoomGithubUserToGithubUser
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

interface UserCache {
    fun cacheUserRepositoriesToDatabase(
        repositories: List<GithubRepositoryDTO>,
        userDTO: GithubUserDTO
    ): Single<List<GithubRepositoryDTO>>

    fun getUserRepositoriesFromDatabase(user: GithubUserDTO): Single<List<GithubRepositoryDTO>>

    fun cacheUserToDatabase(
        githubUser: GithubUserDTO,
    ): Single<GithubUserDTO>

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
                convertGithubRepositoryToRoomGithubUserRepo(user, roomUser?.id)
            }
            localDatabase.repositoryDao.upsert(roomRepos)
        }
        repositories
    }

    override fun getUserRepositoriesFromDatabase(user: GithubUserDTO): Single<List<GithubRepositoryDTO>> =
        Single.fromCallable {
            val roomUser = user.login?.let { localDatabase.userDao.getUserByLogin(it) }
            localDatabase.repositoryDao.getRepositoriesByUserId(userId = roomUser?.id.toString())
                .map { roomGithubUser ->
                    convertRoomGithubUserRepoToGithubRepository(roomGithubUser)
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
        val roomUsers = convertGithubUserToRoomGithubUser(githubUser)
        localDatabase.userDao.upsert(roomUsers)
        githubUser
    }

    override fun getUserDataFromDatabase(user: GithubUserDTO): Single<GithubUserDTO> =
        Single.fromCallable {
            localDatabase.userDao.getUserByLogin(user.login.orEmpty())
                ?.let { convertRoomGithubUserToGithubUser(it) }
        }

}