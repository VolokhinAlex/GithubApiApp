package com.volokhinaleksey.popularlibrariesandroid.ui.images

import android.widget.ImageView
import coil.load

class CoilImageLoader : ImageLoader<ImageView> {

    override fun loadImage(url: String, target: ImageView) {
        target.load(url)
    }

}