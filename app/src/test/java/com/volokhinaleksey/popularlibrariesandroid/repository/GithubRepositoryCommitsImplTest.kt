package com.volokhinaleksey.popularlibrariesandroid.repository

import com.google.common.truth.Truth.assertThat
import com.volokhinaleksey.popularlibrariesandroid.model.GithubCommitsDTO
import com.volokhinaleksey.popularlibrariesandroid.model.GithubRepositoryDTO
import io.reactivex.rxjava3.core.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

class GithubRepositoryCommitsImplTest {

    private lateinit var repository: GithubRepositoryCommits

    @Mock
    private lateinit var apiService: GithubApiService

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        repository = FakeGithubRepositoryCommits(GithubApiHolder(apiService))
    }

    @Test
    fun `checking method calls getUserRepos() return true`() {
        Mockito.`when`(apiService.getRepoCommits("")).thenReturn(Single.just(emptyList()))
        repository.getRepositoryCommits(mock())
        verify(apiService, times(1)).getRepoCommits("")
    }

    @Test
    fun `checking the receipt of data from the getUserRepos() method return true`() {
        val mockSingleGithubCommitsDTO = Single.just(listOf(mock(GithubCommitsDTO::class.java)))
        Mockito.`when`(apiService.getRepoCommits("")).thenReturn(mockSingleGithubCommitsDTO)
        assertThat(repository.getRepositoryCommits(mock())).isEqualTo(mockSingleGithubCommitsDTO)
    }

}