package com.omerilhanli.appcentchallenge.asistive.util

import com.omerilhanli.api_mdl.entity.Photo

object PhotoPathUtil {

    @JvmStatic
    fun getSmallPhotoPath(photo: Photo): String {
        with(photo) {
            return String.format("https://farm%s.staticflickr.com/%s/%s_%s_%c.jpg", farm, server, id, secret, 'm')
        }
    }

    @JvmStatic
    fun getBigPhotoPath(photo: Photo): String {
        with(photo) {
            return String.format("https://farm%s.staticflickr.com/%s/%s_%s_%c.jpg", farm, server, id, secret, 'b')
        }
    }
}