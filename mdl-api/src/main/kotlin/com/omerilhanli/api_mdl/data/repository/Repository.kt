package com.omerilhanli.api_mdl.data.repository

import com.omerilhanli.api_mdl.entity.PhotoInfoParent
import com.omerilhanli.api_mdl.entity.PhotosParent
import io.reactivex.Observable

interface Repository {

    fun getPublishedRecentPhotos(page: Int): Observable<PhotosParent>

    fun getPublishedPhotoInfo(photoId: String): Observable<PhotoInfoParent>
}