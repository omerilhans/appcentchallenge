package com.omerilhanli.api_mdl.entity

import com.google.gson.annotations.SerializedName

data class PhotoInfo(
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("owner")
    val owner: Owner? = null,
    @SerializedName("dateModel")
    val dateModel: DateModel? = null
)
