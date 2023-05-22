package com.marllons.gitusers.utils

import android.graphics.Bitmap
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.Transformation
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.marllons.gitusers.R

fun ImageView.loadImageCenter(url: String, transformation: Transformation<Bitmap?>? = null) {
    transformation?.let {
        Glide.with(this.context)
            .load(url)
            .transform(CenterCrop(), transformation)
            .error(R.drawable.baseline_empty_24)
            .placeholder(R.drawable.baseline_user_24)
            .into(this)
    } ?: run {
        Glide.with(this.context)
            .load(url)
            .transform(CenterCrop())
            .error(R.drawable.baseline_empty_24)
            .placeholder(R.drawable.baseline_user_24)
            .into(this)
    }

}