package com.volokhinaleksey.popularlibrariesandroid.utils

import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import android.os.Parcelable
import com.volokhinaleksey.popularlibrariesandroid.ui.DATA_KEY

inline fun <reified T : Parcelable> Bundle.parcelable(): T? = when {
    SDK_INT >= 33 -> getParcelable(DATA_KEY, T::class.java)
    else -> @Suppress("DEPRECATION") getParcelable(DATA_KEY) as? T
}