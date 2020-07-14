package com.omerilhanli.appcentchallenge.ui.main

import androidx.lifecycle.MutableLiveData
import com.omerilhanli.api_mdl.data.repository.Repository
import com.omerilhanli.api_mdl.data.scheduler.AppScheduler
import com.omerilhanli.api_mdl.entity.PhotosParent
import com.omerilhanli.appcentchallenge.ui.base.BaseViewModel
import javax.inject.Inject
import javax.inject.Singleton

/**
 * MainViewModel hem Main ekranında hem RecentPhotosFragment için kullanılmak üzere dagger ile Singleton olarak üretilir.
 * Her iki yerde de aynı instance'dan yararlanılır.
 */
@Singleton
class MainViewModel @Inject constructor(
    private val repository: Repository, scheduler: AppScheduler
) : BaseViewModel<MainNavigator>(scheduler) {

    // Api requestlerinden gelen response liveData dediğimiz canlı data ile observable olarak view'a sunulur.
    val recentPhotosLiveData: MutableLiveData<PhotosParent> = MutableLiveData()

    // Pagination için mPage = 1'den başlatılır.
    private var mPage: Int = 1

    /**
     * Sayfanın altına geldiğimizde mPage +1 arttırılır.
     */
    fun increasePageNumber() {
        mPage.inc()
    }

    /**
     * Sayfanın en tepesindeyken mPage 1 ile resetlenir.
     */
    fun resetPageNumber() {
        mPage = 1
    }

    /**
     * Apiden son yüklenen resimlerin çekilmesini sağlayan metod.
     */
    fun getRecentPhotos() {
        repository
            .getPublishedRecentPhotos(page = mPage)
            .completion {
                recentPhotosLiveData.value = it
            }
    }

}