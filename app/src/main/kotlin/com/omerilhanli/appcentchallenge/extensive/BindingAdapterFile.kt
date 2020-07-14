package com.omerilhanli.appcentchallenge.extensive

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import com.omerilhanli.appcentchallenge.R

@BindingAdapter("run")
fun View.run(func: () -> Unit) {
    func()
}

@BindingAdapter("bindPhotoPath")
fun ImageView.bindPhotoPath(path: String? = null) {
    path?.let {
        Glide
            .with(context)
            .load(it)
            .centerCrop()
            .placeholder(R.drawable.icn_default_image)
            .into(this)
    }
}

@BindingAdapter("bindRefreshing")
fun SwipeRefreshLayout.bindRefreshing(isRefresh: Boolean) {
    this.isRefreshing = isRefresh
}