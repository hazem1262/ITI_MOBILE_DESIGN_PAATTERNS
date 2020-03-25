package com.hossameldeenaltokhy.unitedtechnicalapp.base


import com.google.gson.annotations.SerializedName

data class ApiErrorResponse(
    @SerializedName("status_code")
    val statusCode: Int?,
    @SerializedName("status_message")
    val statusMessage: String?,
    @SerializedName("success")
    val success: Boolean?
)