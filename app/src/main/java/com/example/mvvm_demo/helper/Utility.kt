package com.example.mvvm_demo.helper

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

class Utility {
    companion object{
        fun isInternetAvailable(context: Context): Boolean {
            var isOnline: Boolean
            val manager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val capabilities: NetworkCapabilities? =
                manager.getNetworkCapabilities(manager.activeNetwork)
            isOnline =
                capabilities != null && capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED);
            return isOnline
        }
    }
}