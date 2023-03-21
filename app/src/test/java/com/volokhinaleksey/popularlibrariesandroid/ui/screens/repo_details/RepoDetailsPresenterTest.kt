package com.volokhinaleksey.popularlibrariesandroid.ui.screens.repo_details

import com.github.terrakok.cicerone.Router
import com.volokhinaleksey.popularlibrariesandroid.model.GithubRepositoryDTO
import com.volokhinaleksey.popularlibrariesandroid.repository.GithubRepositoryCommits
import io.reactivex.rxjava3.android.plugins.RxAndroidPlugins
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.atLeastOnce
import org.mockito.Mockito.mock
import org.mockito.Mockito.times
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.verify

class RepoDetailsPresenterTest {

    private lateinit var presenter: RepoDetailsPresenter

    @Mock
    private lateinit var router: Router

    @Mock
    private lateinit var repository: GithubRepositoryCommits

    @Mock
    private lateinit var viewState: `RepoDetailsView$$State`

    private lateinit var mockGithubRepositoryDTO: GithubRepositoryDTO

    @Before
    fun setUp() {
        mockGithubRepositoryDTO =
            GithubRepositoryDTO(
                id = 0, name = "name", htmlUrl = "", fork = false,
                createdAt = "", forks = 1, commitsUrl = ""
            )
        MockitoAnnotations.openMocks(this)
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        presenter = RepoDetailsPresenter(
            router = router,
            repositoryCommits = repository,
            uiScheduler = AndroidSchedulers.mainThread(),
            githubRepositoryDTO = mockGithubRepositoryDTO
        )
        presenter.setViewState(viewState)
    }

    @Test
    fun `checking method calls repositoryCommitsLoad() return true`() {
        Mockito.`when`(repository.getRepositoryCommits(mockGithubRepositoryDTO)).thenReturn(
            Single.just(
                emptyList()
            )
        )
        presenter.attachView(mock())
        verify(repository, times(1)).getRepositoryCommits(mockGithubRepositoryDTO)
    }

    @Test
    fun `checking method calls backPressed() return true`() {
        presenter.backPressed()
        verify(router, times(1)).exit()
    }

    @Test
    fun `checking the init method call return true`() {
        Mockito.`when`(repository.getRepositoryCommits(mockGithubRepositoryDTO))
            .thenReturn(Single.just(emptyList()))
        presenter.attachView(mock())
        verify(viewState, atLeastOnce()).init()
    }

    @Test
    fun `checking the error return from the method in the repositoryCommitsLoad() method return true`() {
        Mockito.`when`(repository.getRepositoryCommits(mockGithubRepositoryDTO))
            .thenReturn(Single.error(RuntimeException("Something went wrong")))
        presenter.attachView(mock())
        verify(viewState, atLeastOnce()).errorState("Something went wrong")
    }

    @Test
    fun `checking the success state return from the method in the repositoryCommitsLoad() method return true`() {
        Mockito.`when`(repository.getRepositoryCommits(mockGithubRepositoryDTO))
            .thenReturn(Single.just(emptyList()))
        presenter.attachView(mock())
        verify(viewState, atLeastOnce()).successState()
    }

    @After
    fun tearDown() {
        presenter.detachView(mock())
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(null)
    }
}