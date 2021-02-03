package com.s10plus.core_application

import android.os.Build
import android.telecom.Call
import android.telecom.CallScreeningService
import androidx.annotation.RequiresApi

@RequiresApi(Build.VERSION_CODES.N)
class CallScreenService:CallScreeningService() {
    override fun onScreenCall(callDetails: Call.Details) {
        TODO("Not yet implemented")
    }
}