package com.hossameldeenaltokhy.unitedtechnicalapp.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.hossameldeenaltokhy.unitedtechnicalapp.data.models.Address
import com.hossameldeenaltokhy.unitedtechnicalapp.data.models.Company

object UsersTypeConverters {
    @TypeConverter
    @JvmStatic
    fun fromAddress(value: Address): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    @JvmStatic
    fun toAddress(value: String): Address {
        return Gson().fromJson(value, Address::class.java)
    }
    @TypeConverter
    @JvmStatic
    fun fromCompany(value: Company): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    @JvmStatic
    fun toCompany(value: String): Company {
        return Gson().fromJson(value, Company::class.java)
    }
}