package com.volokhinaleksey.popularlibrariesandroid.ui.screens.user

import com.github.terrakok.cicerone.Router
import com.volokhinaleksey.popularlibrariesandroid.di.scopes.UserScopeContainer
import com.volokhinaleksey.popularlibrariesandroid.model.GithubUserDTO
import com.volokhinaleksey.popularlibrariesandroid.navigation.IScreens
import com.volokhinaleksey.popularlibrariesandroid.repository.GithubUserRepository
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

class UserPresenterTest {

    private lateinit var userPresenter: MvpPresenter<UserView>

    @Mock
    private lateinit var userRepository: GithubUserRepository

    @Mock
    private lateinit var router: Router

    private lateinit var mockGithubUserDTO: GithubUserDTO

    @Before
    fun setUp() {
        mockGithubUserDTO = GithubUserDTO(
            login = "name", id = 1, avatarUrl = "name", publicRepos = 1, publicGists = 1,
            followers = 1, following = 1, name = "name", company = "name", blog = "name",
            location = "name", url = "name", reposUrl = "name"
        )
        MockitoAnnotations.openMocks(this)
        userPresenter = UserPresenter(
            githubUser = mockGithubUserDTO,
            uiScheduler = TestScheduler(),
            userRepository = userRepository,
            router = router,
            screens = mock(IScreens::class.java),
            userScopeContainer = mock(UserScopeContainer::class.java)
        )
    }

//    @Test
//    fun `checking method calls loadUserDetailsData() return true`() {
//        Mockito.`when`(userRepository.getUserByLogin(mockGithubUserDTO))
//            .thenReturn(Single.just(mock(GithubUserDTO::class.java)))
//        userPresenter.attachView(mock(UserView::class.java))
//        verify(userRepository, times(1)).getUserByLogin(mockGithubUserDTO)
//    }
//
//    @Test
//    fun `checking method calls loadUserRepositories() return true`() {
//        val mockView = mock(UserView::class.java)
//        Mockito.`when`(userRepository.getUserRepos(mockGithubUserDTO))
//            .thenReturn(Single.just(emptyList()))
//        userPresenter.attachView(mockView)
//        verify(userRepository, times(1)).getUserRepos(mockGithubUserDTO)
//    }

    @Test
    fun `checking method calls backPressed() return true`() {
        Mockito.`when`(router.exit()).then {}
        (userPresenter as UserPresenter).backPressed()
        verify(router, times(1)).exit()
    }
}