package com.omerilhanli.api_mdl.entity

import com.google.gson.annotations.SerializedName

class PhotosParent {
    @SerializedName("photos")
    var photos: Photos? = null
        internal set

    @SerializedName("stat")
    var stat: String? = null
        internal set
}
