package com.volokhinaleksey.popularlibrariesandroid.ui.screens

import android.content.Context
import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SdkSuppress
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.Until
import com.google.common.truth.Truth.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SdkSuppress(minSdkVersion = 27)
class ReposDetailsFragmentUIAutomatorTest {

    private val uiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

    private var context: Context? = null

    private lateinit var packageName: String

    @Before
    fun setUp() {
        context = ApplicationProvider.getApplicationContext()
        packageName = context?.packageName ?: ""
        uiDevice.pressHome()
        val intent = context?.packageManager?.getLaunchIntentForPackage(packageName)
        intent?.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        context?.startActivity(intent)
        uiDevice.wait(
            Until.findObject(By.res(packageName, "user_login")),
            TIMEOUT
        ).click()
        uiDevice.wait(
            Until.findObject(By.res(packageName, "repo_name")),
            TIMEOUT
        ).click()
    }

    @Test
    fun check_ButtonOpenInBrowser_ReturnTrue() {
        val openInBrowser = uiDevice.wait(
            Until.findObject(By.res(packageName, "open_in_browser")), TIMEOUT
        )
        openInBrowser.click()
        uiDevice.waitForWindowUpdate(packageName, 1000)
        assertThat(uiDevice.currentPackageName).isEqualTo("com.android.chrome")
    }

    @Test
    fun check_ButtonBackClick_ReturnTrue() {
        uiDevice.pressBack()
        assertThat(
            uiDevice.wait(
                Until.findObject(By.res(packageName, "user_details_container")),
                TIMEOUT
            )
        ).isNotNull()
    }

    @After
    fun tearDown() {
        context = null
    }

    companion object {
        private const val TIMEOUT = 5000L
    }

}