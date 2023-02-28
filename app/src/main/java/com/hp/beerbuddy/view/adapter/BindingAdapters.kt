package com.hp.beerbuddy.view.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide


@BindingAdapter("app:loadSrc")
fun loadSrc(imageView: ImageView, url: String) {
    Glide.with(imageView.context).load(url).into(imageView)
}
