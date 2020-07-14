package com.omerilhanli.appcentchallenge.extensive

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import com.omerilhanli.appcentchallenge.R

/**
 * Databinding için custom attribute'ları @BindingAdapter ve extension yardımıyla
 * oluşturup kullandığımız top level kotlin dosyası.
 */

/**
 * Xml inflate olurken execute edilen statement'lar için kullanılır
 */
@BindingAdapter("run")
fun View.run(func: () -> Unit) {
    func()
}

/**
 * Photo path'i alarak Glide ile ImageView'a bind etme işlemi için kullanılır
 */
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

/**
 * Boolean parametre ile refresh işlemi dinlenir
 */
@BindingAdapter("bindRefreshing")
fun SwipeRefreshLayout.bindRefreshing(isRefresh: Boolean) {
    this.isRefreshing = isRefresh
}