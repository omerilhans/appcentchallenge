package com.omerilhanli.appcentchallenge.ui.main

import com.omerilhanli.api_mdl.entity.Photo
import com.omerilhanli.appcentchallenge.ui.base.BaseNavigator

interface MainNavigator : BaseNavigator {

    fun showRecentPhotoFragment()

    fun navigateToDetailActivity(photo: Photo)

    fun fetchRecentPhotos(isPageIncrease: Boolean)
}