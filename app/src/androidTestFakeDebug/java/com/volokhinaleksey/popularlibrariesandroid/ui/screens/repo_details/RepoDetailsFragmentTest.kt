package com.volokhinaleksey.popularlibrariesandroid.ui.screens.repo_details

import androidx.core.os.bundleOf
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.volokhinaleksey.popularlibrariesandroid.R
import com.volokhinaleksey.popularlibrariesandroid.model.GithubRepositoryDTO
import com.volokhinaleksey.popularlibrariesandroid.ui.DATA_KEY
import org.junit.After
import org.junit.Before
import org.junit.Test

class RepoDetailsFragmentTest : TestCase() {

    private lateinit var scenarioRepoDetailsFragment: FragmentScenario<RepoDetailsFragment>

    @Before
    fun setUp() {
        scenarioRepoDetailsFragment =
            launchFragmentInContainer(
                themeResId = R.style.Theme_PopularLibrariesAndroid, fragmentArgs = bundleOf(
                    DATA_KEY to GithubRepositoryDTO(
                        1,
                        "mojombo",
                        "https://github.com/mojombo",
                        false,
                        "",
                        1,
                        "https://api.github.com/repos/mojombo/30daysoflaptops.github.io/commits{/sha}"
                    )
                )
            )
    }

    @Test
    fun btnOpenInBrowser_CheckVisible_ReturnTrue() = run {
        RepoDetailsScreen {
            openInBrowserBtn {
                isVisible()
            }
        }
    }

    @Test
    fun btnOpenInBrowser_OnClickAction_ReturnTrue() = run {
        RepoDetailsScreen {
            openInBrowserBtn {
                click()
            }
        }
    }

    @Test
    fun labelForkCount_HasText_ReturnTrue() = run {
        RepoDetailsScreen {
            labelForkCount {
                hasText("Fork count")
            }
        }
    }

    @Test
    fun labelCommits_HasText_ReturnTrue() = run {
        RepoDetailsScreen {
            labelCommits {
                hasText("Commits:")
            }
        }
    }

    @Test
    fun commitsList_CheckVisible_ReturnTrue() = run {
        RepoDetailsScreen {
            commitsList {
                isVisible()
            }
        }
    }

    @Test
    fun check_commitsList_scrollTo_ReturnTrue() = run {
        RepoDetailsScreen {
            commitsList {
                scrollTo(
                    ViewMatchers.hasDescendant(
                        withText("Name: 40")
                    )
                )
            }
        }
    }

    @Test
    fun check_commitsList_ClickOnItem_ReturnTrue() = run {
        RepoDetailsScreen {
            commitsList {
                scrollTo(0)
                click()
            }
        }
    }

    @Test
    fun errorMessage_CheckVisibilityGone_ReturnTrue() = run {
        RepoDetailsScreen {
            errorMessage {
                isGone()
            }
        }
    }

    @After
    fun tearDown() {
        scenarioRepoDetailsFragment.close()
    }

}