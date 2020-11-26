package com.ljj.commonlib.net.network

import android.content.IntentFilter
import android.net.ConnectivityManager
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import java.lang.ref.WeakReference
import java.util.*

object NetStatusLifecycle : DefaultLifecycleObserver {
    private const val TAG = "NetStatusLifecycle"
    private var receiver: WeakReference<NetBroadcastReceiver>? = null
    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        try {
            val mNetBroadcastReceiver = NetBroadcastReceiver()
            receiver = WeakReference<NetBroadcastReceiver>(mNetBroadcastReceiver)
            receiver?.apply {
                val filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
                if (owner is AppCompatActivity) {
                    owner.registerReceiver(get(), filter)
                } else if (owner is Fragment) {
                    Objects.requireNonNull(owner.activity)
                        ?.registerReceiver(get(), filter)
                }
            }
        } catch (e: Exception) {
            Log.e(TAG, "onResume==>e:${e.message}")
        }
    }


    override fun onPause(owner: LifecycleOwner) {
        super.onPause(owner)
        try {
            receiver?.apply {
                if (owner is AppCompatActivity) {
                    owner.unregisterReceiver(get())
                } else if (owner is Fragment) {
                    Objects.requireNonNull(owner.activity)
                        ?.unregisterReceiver(get())
                }
            }
        } catch (e: Exception) {
            Log.e(TAG, "onPause==>e:${e.message}")
        }
    }
}