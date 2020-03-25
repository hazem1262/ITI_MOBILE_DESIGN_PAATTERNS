package com.hossameldeenaltokhy.unitedtechnicalapp.data.models

import com.google.gson.annotations.SerializedName

data class Address(@SerializedName("zipcode")
                   val zipcode: String = "",
                   @SerializedName("geo")
                   val geo: Geo,
                   @SerializedName("suite")
                   val suite: String = "",
                   @SerializedName("city")
                   val city: String = "",
                   @SerializedName("street")
                   val street: String = ""){
    val addressFormatted:String?
    get() {
        return "$street, $city"
    }
}