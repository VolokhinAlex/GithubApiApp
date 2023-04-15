package com.volokhinaleksey.popularlibrariesandroid.ui.screens.repo_details

import androidx.core.os.bundleOf
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.volokhinaleksey.popularlibrariesandroid.R
import com.volokhinaleksey.popularlibrariesandroid.delay
import com.volokhinaleksey.popularlibrariesandroid.model.GithubRepositoryDTO
import com.volokhinaleksey.popularlibrariesandroid.ui.DATA_KEY
import com.volokhinaleksey.popularlibrariesandroid.ui.screens.repo_details.adapter.CommitsAdapter
import com.volokhinaleksey.popularlibrariesandroid.ui.screens.users.adapter.UsersAdapter
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RepoDetailsFragmentTest {

    private lateinit var scenarioRepoDetailsFragment: FragmentScenario<RepoDetailsFragment>

    @Before
    fun setUp() {
        scenarioRepoDetailsFragment =
            launchFragmentInContainer(
                themeResId = R.style.Theme_PopularLibrariesAndroid, fragmentArgs = bundleOf(
                    DATA_KEY to GithubRepositoryDTO(1, "", "https://github.com/", false, "", 1, "")
                )
            )
    }

    @Test
    fun btnOpenInBrowser_CheckVisible_ReturnTrue() {
        onView(withId(R.id.open_in_browser)).check(
            matches(
                ViewMatchers.withEffectiveVisibility(
                    ViewMatchers.Visibility.VISIBLE
                )
            )
        )
    }

    @Test
    fun btnOpenInBrowser_OnClickAction_ReturnTrue() {
        onView(ViewMatchers.isRoot()).perform(delay())
        onView(withId(R.id.open_in_browser)).perform(click())
    }

    @Test
    fun labelForkCount_HasText_ReturnTrue() {
        onView(withId(R.id.label_fork_count)).check(matches(withText("Fork count")))
    }

    @Test
    fun labelCommits_HasText_ReturnTrue() {
        onView(withId(R.id.label_commits)).check(matches(withText("Commits:")))
    }

    @Test
    fun commitsList_CheckVisible_ReturnTrue() {
        onView(withId(R.id.commits_list_container)).check(
            matches(
                ViewMatchers.withEffectiveVisibility(
                    ViewMatchers.Visibility.VISIBLE
                )
            )
        )
    }

    @Test
    fun errorMessage_CheckVisibilityGone_ReturnTrue() {
        onView(withId(R.id.error_message)).check(
            matches(
                ViewMatchers.withEffectiveVisibility(
                    ViewMatchers.Visibility.GONE
                )
            )
        )
    }

    @Test
    fun check_commitsList_scrollTo_ReturnTrue() {
        onView(withId(R.id.commits_list_container))
            .perform(
                RecyclerViewActions.scrollTo<UsersAdapter.ViewHolder>(
                    ViewMatchers.hasDescendant(
                        withText("Name: 32")
                    )
                )
            )
    }

    @Test
    fun check_commitsList_ClickOnItem_ReturnTrue() {
        onView(withId(R.id.commits_list_container)).perform(
            RecyclerViewActions.actionOnItemAtPosition<CommitsAdapter.ViewHolder>(
                40,
                click()
            )
        )
    }

    @After
    fun tearDown() {
        scenarioRepoDetailsFragment.close()
    }
}