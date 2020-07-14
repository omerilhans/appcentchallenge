package com.omerilhanli.appcentchallenge.ui.detail

import androidx.lifecycle.MutableLiveData
import com.omerilhanli.api_mdl.data.repository.Repository
import com.omerilhanli.api_mdl.data.scheduler.AppScheduler
import com.omerilhanli.api_mdl.entity.PhotoInfoMap
import com.omerilhanli.api_mdl.entity.PhotoInfoParent
import com.omerilhanli.appcentchallenge.ui.base.BaseViewModel
import javax.inject.Inject
import javax.inject.Singleton

/**
 * DetailViewModel hem Detail ekranında hem InfoPhotoFragment için kullanılmak üzere dagger ile Singleton olarak üretilir.
 * Her iki yerde de aynı instance'dan yararlanılır.
 */
@Singleton
class DetailViewModel @Inject constructor(
    private val repository: Repository, scheduler: AppScheduler
) : BaseViewModel<DetailNavigator>(scheduler) {

    // Servisten gelen response set edilir.
    val photoInfoLiveData: MutableLiveData<PhotoInfoParent> = MutableLiveData()

    // Tüm gelen Photo ve PhotoInfo dataları map edilir.
    val photoInfoMap: PhotoInfoMap = PhotoInfoMap()

    /**
     * photoId ile apiden yayınlanan resim için info bilgisi alınır.
     */
    fun getPhotoInfo(photoId: String? = null) {
        repository
            .getPublishedPhotoInfo(photoId = photoId ?: "")
            .completion {
                photoInfoLiveData.value = it
            }
    }

}