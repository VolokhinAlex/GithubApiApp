package com.volokhinaleksey.popularlibrariesandroid.ui.screens.users

import android.content.Context
import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SdkSuppress
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.Until
import com.google.common.truth.Truth
import com.volokhinaleksey.popularlibrariesandroid.TIMEOUT
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SdkSuppress(minSdkVersion = 27)
class UsersFragmentUIAutomatorTest {

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
            Until.hasObject(By.pkg(packageName).depth(0)),
            TIMEOUT
        )
    }

    @Test
    fun check_MainScreenStarted_ReturnTrue() {
        Truth.assertThat(uiDevice.findObject(By.res(packageName, "users_list_container")))
            .isNotNull()
    }

    @Test
    fun check_NavigationToUserDetails_ReturnTrue() {
        val navigateItemToUserDetailsScreen = uiDevice.wait(
            Until.findObject(By.res(packageName, "user_login")),
            TIMEOUT
        )
        navigateItemToUserDetailsScreen.click()
        Truth.assertThat(
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

}