package com.volokhinaleksey.popularlibrariesandroid.ui.activity

import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import com.google.common.truth.Truth.assertThat
import com.volokhinaleksey.popularlibrariesandroid.databinding.ActivityMainBinding
import org.junit.After
import org.junit.Before
import org.junit.Test

class MainActivityTest {

    private lateinit var scenario: ActivityScenario<MainActivity>

    @Before
    fun setUp() {
        scenario = ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    fun activity_NotNull_ReturnTrue() {
        scenario.onActivity {
            assertThat(it).isNotNull()
        }
    }

    @Test
    fun activity_isResumed_returnTrue() {
        assertThat(Lifecycle.State.RESUMED).isEqualTo(scenario.state)
    }

    @Test
    fun fragmentContainer_NotNull_ReturnTrue() {
        scenario.onActivity {
            val binding = ActivityMainBinding.inflate(it.layoutInflater)
            assertThat(binding.screensContainer).isNotNull()
        }
    }

    @After
    fun tearDown() {
        scenario.close()
    }
}