package com.volokhinaleksey.popularlibrariesandroid.ui

import android.content.Context
import android.widget.TextView
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SdkSuppress
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiScrollable
import androidx.test.uiautomator.UiSelector
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SdkSuppress(minSdkVersion = 27)
class OpenAnotherAppTestUIAutomator {

    private val uiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

    private lateinit var context: Context

    private lateinit var packageName: String

    @Before
    fun setUp() {
        context = ApplicationProvider.getApplicationContext()
        packageName = context.packageName
        uiDevice.pressBack()
        uiDevice.swipe(
            uiDevice.displayWidth / 2,
            uiDevice.displayHeight - 50,
            uiDevice.displayWidth / 2,
            0,
            10
        )
    }

    @Test
    fun openSettings() {
        val appViews = UiScrollable(UiSelector().scrollable(false))
        val settings =
            appViews.getChildByText(UiSelector().className(TextView::class.java.name), "Settings")
        settings.click()
        assertThat(uiDevice.currentPackageName).isEqualTo("com.android.settings")
    }

    @Test
    fun openInfoAboutDeviceInSettings() {
        val settings = UiScrollable(UiSelector().scrollable(false)).getChildByText(
            UiSelector().className(TextView::class.java.name), "Settings"
        )
        settings.click()
        val aboutDevice = UiScrollable(UiSelector().scrollable(true)).getChildByText(
            UiSelector().className(TextView::class.java.name),
            "About emulated device"
        )
        aboutDevice.click()
        assertThat(uiDevice.findObject(By.text("Device name"))).isNotNull()
    }

    @Test
    fun openTMobileApp() {
        uiDevice.executeShellCommand("monkey -p com.android.stk -c android.intent.category.LAUNCHER 1")
        assertThat(uiDevice.currentPackageName).isEqualTo("com.android.stk")
    }

}