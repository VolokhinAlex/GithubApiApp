package com.volokhinaleksey.popularlibrariesandroid.repository

import com.volokhinaleksey.popularlibrariesandroid.model.GithubRepositoryDTO
import com.volokhinaleksey.popularlibrariesandroid.model.GithubUserDTO
import com.volokhinaleksey.popularlibrariesandroid.room.GithubRoomDatabase
import com.volokhinaleksey.popularlibrariesandroid.utils.NetworkStatus
import com.volokhinaleksey.popularlibrariesandroid.utils.convertRoomGithubUserRepoToGithubRepository
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

interface GithubRepositoriesRepository {
    /**
     * Method for getting a list of user's repositories
     * @param user - User login
     */

    fun getUserRepos(user: GithubUserDTO): Single<List<GithubRepositoryDTO>>
}

class GithubRepositoriesRepositoryImpl(
    private val remoteApiSource: GithubApiService,
    private val networkStatus: NetworkStatus,
    private val localDatabase: GithubRoomDatabase,
    private val repositoriesCache: RepositoriesCache
) : GithubRepositoriesRepository {

    /**
     * Method for getting a list of user's repositories
     * @param user - User data
     */

    override fun getUserRepos(user: GithubUserDTO): Single<List<GithubRepositoryDTO>> =
        networkStatus.isNetworkAvailableSingle().flatMap { isAvailable ->
            if (isAvailable) {
                user.reposUrl?.let { url ->
                    remoteApiSource.getUserRepos(url)
                        .flatMap { repositories ->
                            Single.fromCallable {
                                repositoriesCache.cacheUserRepositoriesToDatabase(
                                    localDatabase,
                                    repositories,
                                    user
                                )
                            }
                        }
                }
                    ?: Single.error<List<GithubRepositoryDTO>>(RuntimeException("User has no repos url"))
                        .subscribeOn(Schedulers.io())
            } else {
                Single.fromCallable {
                    val roomUser = user.login?.let { localDatabase.userDao.findByLogin(it) }
                    localDatabase.repositoryDao.getRepositoriesByUserId(userId = roomUser?.id.toString())
                        .map { roomGithubUser ->
                            convertRoomGithubUserRepoToGithubRepository(roomGithubUser)
                        }
                }
            }
        }.subscribeOn(Schedulers.io())
}