package com.omerilhanli.appcentchallenge.ui.detail

import com.omerilhanli.appcentchallenge.ui.base.BaseNavigator

/**
 * DetailActivity için gerekli davranışlar içerir.
 */
interface DetailNavigator : BaseNavigator {

    fun fetchPhotoInfo()

    fun showInfoPhotoFragment()

    fun navigateBack()
}