package com.volokhinaleksey.popularlibrariesandroid.ui.images

import android.graphics.drawable.Drawable
import android.widget.ImageView

class CachedImageLoader : ImageLoader<ImageView> {

    override fun loadImage(url: String, target: ImageView) {
        target.setImageDrawable(Drawable.createFromPath(url))
    }

}