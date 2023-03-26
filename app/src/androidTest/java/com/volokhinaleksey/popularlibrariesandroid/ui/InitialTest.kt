package com.volokhinaleksey.popularlibrariesandroid.ui

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SdkSuppress
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import androidx.test.uiautomator.UiDevice
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SdkSuppress(minSdkVersion = 27)
class InitialTest {

    private lateinit var context: Context

    private lateinit var packageName: String

    @Before
    fun setUp() {
        context = ApplicationProvider.getApplicationContext()
        packageName = context.packageName
    }

    @Test
    fun check_UIDevice_NotNull_ReturnTrue() {
        val uiDevice = UiDevice.getInstance(getInstrumentation())
        assertThat(uiDevice).isNotNull()
    }

    @Test
    fun check_AppPackage_NotNull_ReturnTrue() {
        assertThat(packageName).isNotNull()
    }

    @Test
    fun check_MainActivityIntent_NotNull() {
        val intent = context.packageManager.getLaunchIntentForPackage(packageName)
        assertThat(intent).isNotNull()
    }

}