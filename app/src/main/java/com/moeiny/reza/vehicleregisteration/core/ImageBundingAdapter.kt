package com.moeiny.reza.vehicleregisteration.core

import android.webkit.WebView
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
/**
 * An object to handle loading a photo onto an imageView on XML file by using Binding.
 * Photo has fetch from network by using its url.
 */

object ImageBindingAdapter {
    @JvmStatic
    @BindingAdapter("android:src")
    fun setImageUrl(view: ImageView, url: String) {
        Glide.with(view.context).load(url).into(view)
    }
}