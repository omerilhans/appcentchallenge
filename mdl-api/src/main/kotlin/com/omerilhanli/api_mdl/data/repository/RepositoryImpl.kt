package com.omerilhanli.api_mdl.data.repository

import com.omerilhanli.api_mdl.api.Api

class RepositoryImpl constructor(private val api: Api) : Repository {

    override fun getPublishedRecentPhotos(page: Int) = api.getRecent(page = page)

    override fun getPublishedPhotoInfo(photoId: String) = api.getInfo(photoId = photoId)
}