package com.omerilhanli.appcentchallenge.ui.detail

import androidx.lifecycle.MutableLiveData
import com.omerilhanli.api_mdl.data.repository.Repository
import com.omerilhanli.api_mdl.data.scheduler.AppScheduler
import com.omerilhanli.api_mdl.entity.PhotoInfoMap
import com.omerilhanli.api_mdl.entity.PhotoInfoParent
import com.omerilhanli.appcentchallenge.ui.base.BaseViewModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DetailViewModel @Inject constructor(
    private val repository: Repository, scheduler: AppScheduler
) : BaseViewModel<DetailNavigator>(scheduler) {

    val photoInfo: MutableLiveData<PhotoInfoParent> = MutableLiveData()

    val photoInfoMap: PhotoInfoMap = PhotoInfoMap()

    fun getPhotoInfo(photoId: String? = null) {
        repository
            .getPublishedPhotoInfo(photoId = photoId ?: "")
            .completion {
                photoInfo.value = it
            }
    }

}