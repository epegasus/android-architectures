package com.sohaib.modularization.core.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadImage(any: Any) {
    Glide.with(context).load(any).into(this)
}