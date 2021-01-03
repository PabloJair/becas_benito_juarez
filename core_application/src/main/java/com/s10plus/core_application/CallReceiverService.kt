package com.s10plus.core_application

import android.app.Service
import android.content.Intent
import android.content.IntentFilter
import android.os.CountDownTimer
import android.os.IBinder
import com.blankj.utilcode.util.LogUtils


class CallReceiverService: Service() {
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        LogUtils.d("ERROR")
        ///registerReceiver(CallReceiver(),IntentFilter.NO_MATCH_ACTION)
        return START_STICKY
    }
}