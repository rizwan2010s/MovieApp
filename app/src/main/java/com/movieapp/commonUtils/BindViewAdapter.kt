package com.movieapp.commonUtils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.movieapp.commonUtils.Constant.IMAGE_URL
import com.movieapp.R

@BindingAdapter("urlToImage")
fun urlToImage(view: ImageView, url: String?) {
        url?.let {
                val options =
                        RequestOptions.placeholderOf(R.drawable.loading).error(R.drawable.error)
                Glide.with(view).setDefaultRequestOptions(options).load(IMAGE_URL + url)
                        .into(view)
        }
}

