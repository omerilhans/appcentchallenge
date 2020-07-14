package com.omerilhanli.appcentchallenge.asistive.alert

import android.app.Activity
import android.app.AlertDialog

/**
 * Tüm uyarılar için alert yapısı sağlar.
 */
object AppAlert {
    fun showAlert(activity: Activity, title: String, message: String, buttonTitle: String) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(activity)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton(buttonTitle) { dialog, _ -> dialog.dismiss() }
        builder.show()
    }
}