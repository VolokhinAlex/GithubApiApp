package com.volokhinaleksey.popularlibrariesandroid.ui.screens.user

import androidx.core.os.bundleOf
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.volokhinaleksey.popularlibrariesandroid.R
import com.volokhinaleksey.popularlibrariesandroid.delay
import com.volokhinaleksey.popularlibrariesandroid.model.GithubUserDTO
import com.volokhinaleksey.popularlibrariesandroid.ui.DATA_KEY
import com.volokhinaleksey.popularlibrariesandroid.ui.screens.user.adapter.ReposAdapter
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UserFragmentTest {

    private lateinit var scenario: FragmentScenario<UserFragment>

    @Before
    fun setUp() {
        scenario = launchFragmentInContainer(
            themeResId = R.style.Theme_PopularLibrariesAndroid,
            fragmentArgs = bundleOf(
                DATA_KEY to GithubUserDTO(
                    "mojombo",
                    1,
                    "https://avatars.githubusercontent.com/u/1?v=4",
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    "https://api.github.com/users/mojombo",
                    "https://api.github.com/users/mojombo/repos"
                )
            )
        )
    }

    @Test
    fun fragment_NotNull_ReturnTrue() {
        scenario.onFragment {
            assertThat(it).isNotNull()
        }
    }

    @Test
    fun errorMessage_CheckVisibilityGone_ReturnTrue() {
        onView(isRoot()).perform(delay())
        onView(withId(R.id.error_message)).check(matches(withEffectiveVisibility(Visibility.GONE)))
    }

    @Test
    fun userImage_CheckVisible_ReturnTrue() {
        onView(isRoot()).perform(delay())
        onView(withId(R.id.user_image)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
    }

    @Test
    fun userLogin_CheckVisible_ReturnTrue() {
        onView(isRoot()).perform(delay())
        onView(withId(R.id.user_login)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
    }

    @Test
    fun personName_CheckVisible_ReturnTrue() {
        onView(isRoot()).perform(delay())
        onView(withId(R.id.person_name)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
    }

    @Test
    fun labelRepos_CheckVisible_ReturnTrue() {
        onView(isRoot()).perform(delay())
        onView(withId(R.id.label_repositories)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
    }

    @Test
    fun publicRepos_CheckVisible_ReturnTrue() {
        onView(isRoot()).perform(delay())
        onView(withId(R.id.public_repos)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
    }

    @Test
    fun labelGists_CheckVisible_ReturnTrue() {
        onView(isRoot()).perform(delay())
        onView(withId(R.id.label_public_gists)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
    }

    @Test
    fun publicGists_CheckVisible_ReturnTrue() {
        onView(isRoot()).perform(delay())
        onView(withId(R.id.public_gists)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
    }

    @Test
    fun labelFollowers_CheckVisible_ReturnTrue() {
        onView(isRoot()).perform(delay())
        onView(withId(R.id.label_followers)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
    }

    @Test
    fun followers_CheckVisible_ReturnTrue() {
        onView(isRoot()).perform(delay())
        onView(withId(R.id.followers)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
    }

    @Test
    fun labelFollowing_CheckVisible_ReturnTrue() {
        onView(isRoot()).perform(delay())
        onView(withId(R.id.label_following)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
    }

    @Test
    fun following_CheckVisible_ReturnTrue() {
        onView(isRoot()).perform(delay())
        onView(withId(R.id.following)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
    }

    @Test
    fun labelReposList_CheckVisible_ReturnTrue() {
        onView(isRoot()).perform(delay())
        onView(withId(R.id.label_repositories_list)).check(
            matches(
                withEffectiveVisibility(
                    Visibility.VISIBLE
                )
            )
        )
    }

    @Test
    fun reposList_CheckVisible_ReturnTrue() {
        onView(isRoot()).perform(delay())
        onView(withId(R.id.repos_list_container)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
    }

    @Test
    fun labelRepos_HasTest_ReturnTrue() {
        onView(isRoot()).perform(delay())
        onView(withId(R.id.label_repositories)).check(matches(withText("Repositories:")))
    }

    @Test
    fun labelPublicGists_HasTest_ReturnTrue() {
        onView(isRoot()).perform(delay())
        onView(withId(R.id.label_public_gists)).check(matches(withText("Gists")))
    }

    @Test
    fun labelFollowers_HasTest_ReturnTrue() {
        onView(isRoot()).perform(delay())
        onView(withId(R.id.label_followers)).check(matches(withText("Followers:")))
    }

    @Test
    fun labelFollowing_HasTest_ReturnTrue() {
        onView(isRoot()).perform(delay())
        onView(withId(R.id.label_following)).check(matches(withText("Following:")))
    }

    @Test
    fun labelReposList_HasTest_ReturnTrue() {
        onView(isRoot()).perform(delay())
        onView(withId(R.id.label_repositories_list)).check(matches(withText("Repositories:")))
    }

    @Test
    fun check_ReposList_ScrollTo_ReturnTrue() {
        onView(isRoot()).perform(delay())
        onView(withId(R.id.repos_list_container)).perform(
            RecyclerViewActions.scrollTo<ReposAdapter.ViewHolder>(
                hasDescendant(
                    withText("git")
                )
            )
        )
    }

    @Test
    fun check_ReposList_ClickOnItem_ReturnTrue() {
        onView(isRoot()).perform(delay())
        onView(withId(R.id.repos_list_container)).perform(
            RecyclerViewActions.actionOnItemAtPosition<ReposAdapter.ViewHolder>(
                15,
                click()
            )
        )
    }

    fun tearDown() {
        scenario.close()
    }

}