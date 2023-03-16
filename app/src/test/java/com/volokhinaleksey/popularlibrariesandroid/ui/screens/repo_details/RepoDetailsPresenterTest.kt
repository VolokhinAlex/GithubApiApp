package com.volokhinaleksey.popularlibrariesandroid.ui.screens.repo_details

import com.github.terrakok.cicerone.Router
import com.volokhinaleksey.popularlibrariesandroid.model.GithubRepositoryDTO
import com.volokhinaleksey.popularlibrariesandroid.repository.GithubRepositoryCommits
import com.volokhinaleksey.popularlibrariesandroid.ui.screens.users.UsersPresenter
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.TestScheduler
import moxy.MvpPresenter
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.times
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.verify

class RepoDetailsPresenterTest {

    private lateinit var presenter: MvpPresenter<RepoDetailsView>

    @Mock
    private lateinit var router: Router

    @Mock
    private lateinit var repository: GithubRepositoryCommits

    private lateinit var mockGithubRepositoryDTO: GithubRepositoryDTO

    @Before
    fun setUp() {
        mockGithubRepositoryDTO =
            GithubRepositoryDTO(
                id = 0, name = "name", htmlUrl = "", fork = false,
                createdAt = "", forks = 1, commitsUrl = ""
            )
        MockitoAnnotations.openMocks(this)
        presenter = RepoDetailsPresenter(
            router = router,
            repositoryCommits = repository,
            uiScheduler = TestScheduler(),
            githubRepositoryDTO = mockGithubRepositoryDTO
        )
    }

    @Test
    fun `checking method calls repositoryCommitsLoad() return true`() {
        Mockito.`when`(repository.getRepositoryCommits(mockGithubRepositoryDTO)).thenReturn(
            Single.just(
                emptyList()
            )
        )
        presenter.attachView(mock(RepoDetailsView::class.java))
        verify(repository, times(1)).getRepositoryCommits(mockGithubRepositoryDTO)
    }

    @Test
    fun `checking method calls backPressed() return true`() {
        Mockito.`when`(router.exit()).then {}
        (presenter as RepoDetailsPresenter).backPressed()
        verify(router, times(1)).exit()
    }
}