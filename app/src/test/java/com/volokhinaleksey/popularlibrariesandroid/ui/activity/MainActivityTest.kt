package com.volokhinaleksey.popularlibrariesandroid.ui.activity

import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import com.google.common.truth.Truth.assertThat

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class MainActivityTest {

    private lateinit var scenario: ActivityScenario<MainActivity>

    @Before
    fun setUp() {
        scenario = ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    fun `checking activity is not null return true`() {
        scenario.onActivity {
            assertThat(it).isNotNull()
        }
    }

    @Test
    fun `checking activity resumed state return true`() {
        scenario.onActivity {
            assertThat(scenario.state).isEqualTo(Lifecycle.State.RESUMED)
        }
    }

    @After
    fun tearDown() {
        scenario.close()
    }
}