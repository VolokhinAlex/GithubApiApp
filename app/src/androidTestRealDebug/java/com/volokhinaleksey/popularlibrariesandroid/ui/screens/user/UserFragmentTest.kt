package com.volokhinaleksey.popularlibrariesandroid.ui.screens.user

import androidx.core.os.bundleOf
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.matcher.ViewMatchers
import com.google.common.truth.Truth.assertThat
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.volokhinaleksey.popularlibrariesandroid.R
import com.volokhinaleksey.popularlibrariesandroid.model.GithubUserDTO
import com.volokhinaleksey.popularlibrariesandroid.ui.DATA_KEY
import org.junit.After
import org.junit.Before
import org.junit.Test

class UserFragmentTest : TestCase() {

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
    fun labelRepos_CheckVisible_ReturnTrue() = run {
        UserScreen {
            labelRepositories {
                isVisible()
            }
        }
    }

    @Test
    fun publicRepos_CheckVisible_ReturnTrue() = run {
        UserScreen {
            publicRepos {
                isVisible()
            }
        }
    }

    @Test
    fun labelGists_CheckVisible_ReturnTrue() = run {
        UserScreen {
            labelPublicGists {
                isVisible()
            }
        }
    }

    @Test
    fun publicGists_CheckVisible_ReturnTrue() = run {
        UserScreen {
            publicGists {
                isVisible()
            }
        }
    }

    @Test
    fun labelFollowers_CheckVisible_ReturnTrue() = run {
        UserScreen {
            labelFollowers {
                isVisible()
            }
        }
    }

    @Test
    fun followers_CheckVisible_ReturnTrue() = run {
        UserScreen {
            followers {
                isVisible()
            }
        }
    }

    @Test
    fun labelFollowing_CheckVisible_ReturnTrue() = run {
        UserScreen {
            labelFollowing {
                isVisible()
            }
        }
    }

    @Test
    fun following_CheckVisible_ReturnTrue() = run {
        UserScreen {
            following {
                isVisible()
            }
        }
    }

    @Test
    fun labelReposList_CheckVisible_ReturnTrue() = run {
        UserScreen {
            labelRepositoriesList {
                isVisible()
            }
        }
    }

    @Test
    fun reposList_CheckVisible_ReturnTrue() = run {
        UserScreen {
            reposList {
                isVisible()
            }
        }
    }

    @Test
    fun labelRepos_HasTest_ReturnTrue() = run {
        UserScreen {
            labelRepositories {
                hasText("Repositories:")
            }
        }
    }

    @Test
    fun labelPublicGists_HasTest_ReturnTrue() = run {
        UserScreen {
            labelPublicGists {
                hasText("Gists")
            }
        }
    }

    @Test
    fun labelFollowers_HasTest_ReturnTrue() = run {
        UserScreen {
            labelFollowers {
                hasText("Followers:")
            }
        }
    }

    @Test
    fun labelFollowing_HasTest_ReturnTrue() = run {
        UserScreen {
            labelFollowing {
                hasText("Following:")
            }
        }
    }

    @Test
    fun labelReposList_HasTest_ReturnTrue() = run {
        UserScreen {
            labelRepositoriesList {
                hasText("Repositories:")
            }
        }
    }

    @Test
    fun check_ReposList_ScrollTo_ReturnTrue() = run {
        UserScreen {
            reposList {
                scrollTo(
                    ViewMatchers.hasDescendant(
                        ViewMatchers.withText("git")
                    )
                )
            }
        }
    }

    @Test
    fun check_ReposList_ClickOnItem_ReturnTrue() = run {
        UserScreen {
            reposList {
                scrollTo(15)
                click()
            }
        }
    }

    @Test
    fun errorMessage_CheckVisibilityGone_ReturnTrue() = run {
        UserScreen {
            errorMessage {
                isGone()
            }
        }
    }

    @After
    fun tearDown() {
        scenario.close()
    }
}