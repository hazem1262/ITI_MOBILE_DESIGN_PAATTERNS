package com.hossameldeenaltokhy.unitedtechnicalapp.base

import android.app.Application
import android.content.IntentFilter
import androidx.room.Room
import com.hossameldeenaltokhy.unitedtechnicalapp.R
import com.hossameldeenaltokhy.unitedtechnicalapp.data.local.UserDB
import com.hossameldeenaltokhy.unitedtechnicalapp.utils.ConnectivityReceiver
import com.hossameldeenaltokhy.unitedtechnicalapp.utils.ConnectivityUtils

class UnitedTechnicalApplication: Application() {
    companion object{
        var connectivityUtils: ConnectivityUtils? = null
        var db:UserDB? = null
    }

    override fun onCreate() {
        super.onCreate()
        // register broad cast receiver to be notified when network status changed
        registerReceiver(ConnectivityReceiver(), IntentFilter(resources.getString(R.string.changeAction)))
        connectivityUtils = ConnectivityUtils(applicationContext)
        db = Room
            .databaseBuilder(applicationContext, UserDB::class.java, "UserDB")
            .allowMainThreadQueries()
            .build()

    }
}