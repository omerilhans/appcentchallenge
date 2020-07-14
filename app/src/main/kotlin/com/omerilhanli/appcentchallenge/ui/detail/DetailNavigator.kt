package com.omerilhanli.appcentchallenge.ui.detail

import com.omerilhanli.appcentchallenge.ui.base.BaseNavigator

interface DetailNavigator : BaseNavigator {

    fun fetchPhotoInfo()

    fun showInfoPhotoFragment()

    fun navigateBack()
}