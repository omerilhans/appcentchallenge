package com.omerilhanli.appcentchallenge.extensive

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Parcelable
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.omerilhanli.appcentchallenge.R

/**
 * Bazı yardımcı extension methodların tutulduğu kotlin dosyası
 */


/**
 * Activity class type verilerek yeni activity başlatan generic extension metodu.
 */
fun <T : Parcelable> Activity.startThis(activityClass: Class<*>, intentKey: String = "", intentValue: T? = null, isFinish: Boolean = false) {
    val intent = Intent(this, activityClass)
    intentValue?.let {
        intent.putExtra(intentKey, it)
    }
    startActivity(intent)
    if (isFinish)
        finish()
}

fun SwipeRefreshLayout.startRefresh() {
    if (isRefreshing.not()) {
        isRefreshing = true
    }
}

fun SwipeRefreshLayout.stopRefresh() {
    if (isRefreshing) {
        isRefreshing = false
    }
}

fun Context.openLinkOnBrowser(url: String?) {
    url?.let {
        val builder = CustomTabsIntent.Builder()
        builder.setToolbarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark))
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(this, Uri.parse(url))
    }
}
