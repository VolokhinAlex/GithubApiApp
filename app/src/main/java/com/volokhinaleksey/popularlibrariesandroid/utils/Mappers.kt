package com.volokhinaleksey.popularlibrariesandroid.utils

import com.volokhinaleksey.popularlibrariesandroid.model.*

fun mapToRoomGithubUser(githubUser: GithubUserDTO): RoomGithubUser =
    RoomGithubUser(
        id = githubUser.id,
        login = githubUser.login,
        avatarUrl = githubUser.avatarUrl,
        publicRepos = githubUser.publicRepos,
        publicGists = githubUser.publicGists,
        followers = githubUser.followers,
        following = githubUser.following,
        name = githubUser.name,
        company = githubUser.company,
        blog = githubUser.blog,
        location = githubUser.location,
        url = githubUser.url,
        reposUrl = githubUser.reposUrl
    )

fun mapToGithubUser(roomGithubUser: RoomGithubUser): GithubUserDTO =
    GithubUserDTO(
        login = roomGithubUser.login,
        id = roomGithubUser.id,
        avatarUrl = roomGithubUser.avatarUrl,
        publicRepos = roomGithubUser.publicRepos,
        publicGists = roomGithubUser.publicGists,
        followers = roomGithubUser.followers,
        following = roomGithubUser.following,
        name = roomGithubUser.name,
        company = roomGithubUser.company,
        blog = roomGithubUser.blog,
        location = roomGithubUser.location,
        url = roomGithubUser.url,
        reposUrl = roomGithubUser.reposUrl
    )


fun mapToRoomGithubUserRepo(
    githubRepo: GithubRepositoryDTO,
    userId: Long?
): RoomGithubUserRepo =
    RoomGithubUserRepo(
        id = githubRepo.id ?: 0,
        userId = userId ?: 0,
        name = githubRepo.name ?: "",
        forksCount = githubRepo.forks ?: 0,
        htmlUrl = githubRepo.htmlUrl,
        fork = githubRepo.fork,
        createdAt = githubRepo.createdAt
    )

fun mapToGithubRepository(roomGithubRepo: RoomGithubUserRepo): GithubRepositoryDTO =
    GithubRepositoryDTO(
        id = roomGithubRepo.id,
        name = roomGithubRepo.name,
        htmlUrl = roomGithubRepo.htmlUrl,
        fork = roomGithubRepo.fork,
        createdAt = roomGithubRepo.createdAt,
        forks = roomGithubRepo.forksCount,
        commitsUrl = null
    )

fun mapToRoomGithubRepoCommits(githubRepoCommits: GithubCommitsDTO, repoId: Long) =
    RoomGithubRepoCommits(
        sha = githubRepoCommits.sha.orEmpty(),
        commitMessage = githubRepoCommits.commit?.message.orEmpty(),
        committerName = githubRepoCommits.commit?.committer?.name.orEmpty(),
        committerEmail = githubRepoCommits.commit?.committer?.email.orEmpty(),
        commitDate = githubRepoCommits.commit?.committer?.date.orEmpty(),
        repositoryId = repoId
    )

fun mapToGithubCommitsDTO(githubRepoCommits: RoomGithubRepoCommits) = GithubCommitsDTO(
    sha = githubRepoCommits.sha,
    commit = CommitDTO(
        committer = CommitterDTO(
            name = githubRepoCommits.committerName,
            email = githubRepoCommits.committerEmail,
            date = githubRepoCommits.commitDate
        ), message = githubRepoCommits.commitMessage
    )
)