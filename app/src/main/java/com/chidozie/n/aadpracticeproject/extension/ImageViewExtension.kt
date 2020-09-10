package com.chidozie.n.aadpracticeproject.extension

import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide

fun ImageView.loadImageWithGlide(imageUrl: String, @DrawableRes placeholder: Int) {
    Glide
        .with(this)
        .load(imageUrl)
        .placeholder(placeholder)
        .error(placeholder)
        .into(this)
}
