package com.omerilhanli.appcentchallenge.ui.main

import androidx.lifecycle.MutableLiveData
import com.omerilhanli.api_mdl.data.repository.Repository
import com.omerilhanli.api_mdl.data.scheduler.AppScheduler
import com.omerilhanli.api_mdl.entity.PhotosParent
import com.omerilhanli.appcentchallenge.ui.base.BaseViewModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainViewModel @Inject constructor(
    private val repository: Repository, scheduler: AppScheduler
) : BaseViewModel<MainNavigator>(scheduler) {

    val recentPhotos: MutableLiveData<PhotosParent> = MutableLiveData()

    private var mPage: Int = 1

    fun increasePageNumber() {
        mPage.inc()
    }

    fun resetPageNumber() {
        mPage = 1
    }

    fun getRecentPhotos() {
        repository
            .getPublishedRecentPhotos(page = mPage)
            .completion {
                recentPhotos.value = it
            }
    }

}