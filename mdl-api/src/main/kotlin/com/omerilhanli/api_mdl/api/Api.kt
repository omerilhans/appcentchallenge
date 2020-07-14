package com.omerilhanli.api_mdl.api

import com.omerilhanli.api_mdl.BuildConfig
import com.omerilhanli.api_mdl.constant.Attribute.CONST_API_KEY
import com.omerilhanli.api_mdl.constant.Attribute.CONST_FORMAT
import com.omerilhanli.api_mdl.constant.Attribute.CONST_FORMAT_VALUE
import com.omerilhanli.api_mdl.constant.Attribute.CONST_NO_JSON_CALLBACK
import com.omerilhanli.api_mdl.constant.Attribute.CONST_NO_JSON_CALLBACK_VALUE
import com.omerilhanli.api_mdl.constant.Attribute.CONST_PAGE
import com.omerilhanli.api_mdl.constant.Attribute.CONST_PER_PAGE
import com.omerilhanli.api_mdl.constant.Attribute.CONST_PER_PAGE_VALUE
import com.omerilhanli.api_mdl.constant.Attribute.CONST_PHOTO_ID
import com.omerilhanli.api_mdl.constant.EndPoint.END_GET_INFO
import com.omerilhanli.api_mdl.constant.EndPoint.END_GET_RECENT
import com.omerilhanli.api_mdl.entity.PhotoInfoParent
import com.omerilhanli.api_mdl.entity.PhotosParent
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET(END_GET_RECENT)
    fun getRecent(
        @Query(CONST_API_KEY) apiKey: String = BuildConfig.KEY_API_VALUE,
        @Query(CONST_FORMAT) format: String = CONST_FORMAT_VALUE,
        @Query(CONST_NO_JSON_CALLBACK) noJsonCallback: Int = CONST_NO_JSON_CALLBACK_VALUE,
        @Query(CONST_PER_PAGE) perPage: Int = CONST_PER_PAGE_VALUE,
        @Query(CONST_PAGE) page: Int
    ): Observable<PhotosParent>

    @GET(END_GET_INFO)
    fun getInfo(
        @Query(CONST_API_KEY) apiKey: String = BuildConfig.KEY_API_VALUE,
        @Query(CONST_FORMAT) format: String = CONST_FORMAT_VALUE,
        @Query(CONST_NO_JSON_CALLBACK) noJsonCallback: Int = CONST_NO_JSON_CALLBACK_VALUE,
        @Query(CONST_PHOTO_ID) photoId: String
    ): Observable<PhotoInfoParent>
}