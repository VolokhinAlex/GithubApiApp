package com.volokhinaleksey.popularlibrariesandroid.ui.screens.user

import com.github.terrakok.cicerone.Router
import com.volokhinaleksey.popularlibrariesandroid.di.scopes.UserScopeContainer
import com.volokhinaleksey.popularlibrariesandroid.model.GithubUserDTO
import com.volokhinaleksey.popularlibrariesandroid.navigation.IScreens
import com.volokhinaleksey.popularlibrariesandroid.repository.GithubUserRepository
import io.reactivex.rxjava3.android.plugins.RxAndroidPlugins
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.times
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.verify

class UserPresenterTest {

    private lateinit var userPresenter: UserPresenter

    @Mock
    private lateinit var userRepository: GithubUserRepository

    @Mock
    private lateinit var router: Router

    @Mock
    private lateinit var viewState: `UserView$$State`

    private lateinit var mockGithubUserDTO: GithubUserDTO

    @Before
    fun setUp() {
        mockGithubUserDTO = GithubUserDTO(
            login = "name", id = 1, avatarUrl = "name", publicRepos = 1, publicGists = 1,
            followers = 1, following = 1, name = "name", company = "name", blog = "name",
            location = "name", url = "name", reposUrl = "name"
        )
        MockitoAnnotations.openMocks(this)
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        userPresenter = UserPresenter(
            githubUser = mockGithubUserDTO,
            uiScheduler = AndroidSchedulers.mainThread(),
            userRepository = userRepository,
            router = router,
            screens = mock(IScreens::class.java),
            userScopeContainer = mock(UserScopeContainer::class.java)
        )
        userPresenter.setViewState(viewState)
        Mockito.`when`(userRepository.getUserByLogin(mockGithubUserDTO))
            .thenReturn(Single.just(mockGithubUserDTO))
        Mockito.`when`(userRepository.getUserRepos(mockGithubUserDTO))
            .thenReturn(Single.just(emptyList()))
    }

    @Test
    fun `checking method calls loadUserDetailsData() return true`() {
        userPresenter.attachView(mock())
        verify(userRepository, times(1)).getUserByLogin(mockGithubUserDTO)
    }

    @Test
    fun `checking method calls loadUserRepositories() return true`() {
        userPresenter.attachView(mock())
        verify(userRepository, times(1)).getUserRepos(mockGithubUserDTO)
    }

    @Test
    fun `checking method calls backPressed() return true`() {
        userPresenter.backPressed()
        verify(router, times(1)).exit()
    }

    @Test
    fun `checking the loading status return true`() {
        userPresenter.attachView(mock())
        verify(viewState, Mockito.atLeastOnce()).loadingState()
    }

    @Test
    fun `checking the init method call return true`() {
        userPresenter.attachView(mock())
        verify(viewState, Mockito.atLeastOnce()).init()
    }

    @Test
    fun `checking the error return from the method in the loadUsersData() method return true`() {
        Mockito.`when`(userRepository.getUserRepos(mockGithubUserDTO))
            .thenReturn(Single.error(RuntimeException("Something went wrong")))
        userPresenter.attachView(mock())
        verify(viewState, Mockito.atLeastOnce()).errorState("Something went wrong")
    }

    @Test
    fun `checking the success state return from the method in the loadUsersData() method return true`() {
        Mockito.`when`(userRepository.getUserRepos(mockGithubUserDTO))
            .thenReturn(Single.just(emptyList()))
        userPresenter.attachView(mock())
        verify(viewState, Mockito.atLeastOnce()).successState()
    }

    @After
    fun tearDown() {
        userPresenter.detachView(mock())
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(null)
    }

}