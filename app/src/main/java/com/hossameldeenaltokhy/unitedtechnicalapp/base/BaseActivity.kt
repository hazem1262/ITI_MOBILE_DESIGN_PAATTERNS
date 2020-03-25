package com.hossameldeenaltokhy.unitedtechnicalapp.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.hossameldeenaltokhy.unitedtechnicalapp.R
import com.hossameldeenaltokhy.unitedtechnicalapp.utils.ConnectivityReceiver
import kotlinx.android.synthetic.main.activity_user_list.*

open class BaseActivity: AppCompatActivity(), ConnectivityReceiver.ConnectivityReceiverListener  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // register the connectivity receiver to handle network changes
        ConnectivityReceiver.connectivityReceiverListener = this
    }

    override fun onNetworkConnectionChanged(isConnected: Boolean) {
        if (!isConnected){
            showErrorMessage(this.getString(R.string.no_internet_connection))
        }
    }
    fun showErrorMessage(error:String){
        val snack =
            Snackbar.make(parentConstraintLayOut, error, Snackbar.LENGTH_LONG)
        snack.show()
    }

    override fun onDestroy() {
        super.onDestroy()
        // unsubscribe from the connection
        ConnectivityReceiver.connectivityReceiverListener = null
    }
}