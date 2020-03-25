package com.hossameldeenaltokhy.unitedtechnicalapp.utils


import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Build
import android.os.PowerManager
import android.util.Log


/**
 * Created by hazem.ashraf on 6/15/2018.
 * http://devdeeds.com/android-kotlin-listen-to-internet-connection-using-broadcastreceiver/
 */

class ConnectivityReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, arg1: Intent) {
	    connectivityReceiverListener?.onNetworkConnectionChanged(
            isConnectedOrConnecting(context)
        )
    }

    interface ConnectivityReceiverListener {
        fun onNetworkConnectionChanged(isConnected: Boolean)
    }

    companion object {

        var connectivityReceiverListener: ConnectivityReceiverListener? = null

	    private fun isConnectedOrConnecting(context: Context): Boolean {
		    val connMgr = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
		    val networkInfo = connMgr.activeNetworkInfo

		    // it appears that calling the function twice solves the problem!! (some devices turnoff network access to apps that have been in the background for too long)
		    Log.d(
                ConnectivityReceiver::class.java.simpleName, "isConnectedOrConnecting -- isInteractive: ${isInteractive(
                    context
                )} - isConnected: ${networkInfo != null && networkInfo.isConnectedOrConnecting}")
		    return if (isInteractive(context)) networkInfo != null && networkInfo.isConnected else true
	    }

	    // Checks if the device is awake
	    private fun isInteractive(context: Context): Boolean {
		    val powerManager = context.getSystemService(Context.POWER_SERVICE) as PowerManager

		    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
			    powerManager.isInteractive && !powerManager.isDeviceIdleMode

		    } else {
			    powerManager.isInteractive
		    }
	    }
    }
}
