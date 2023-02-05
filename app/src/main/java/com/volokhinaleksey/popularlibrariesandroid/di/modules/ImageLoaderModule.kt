package com.volokhinaleksey.popularlibrariesandroid.di.modules

import android.widget.ImageView
import com.volokhinaleksey.popularlibrariesandroid.ui.images.CoilImageLoader
import com.volokhinaleksey.popularlibrariesandroid.ui.images.ImageLoader
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ImageLoaderModule {

    @Provides
    @Singleton
    fun imageLoader(): ImageLoader<ImageView> = CoilImageLoader()

}