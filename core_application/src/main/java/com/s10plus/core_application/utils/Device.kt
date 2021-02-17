package com.s10plus.core_application.utils

import android.annotation.SuppressLint
import android.content.Context
import android.telephony.TelephonyManager
import android.provider.Settings
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.s10plus.core_application.GlobalSettings
import com.s10plus.core_application.S10PlusApplication
import kotlin.jvm.Throws


object Device {// Archos 133 Oxygen : 6.0.1

    const val lada = "+521"
    var serialCache = ""
     fun getSerialNumber(): String {
        return GlobalSettings.getSerial()
    }


    fun getLineNumberPhone(scenario: Context): String {

        return try {
            val tMgr = scenario.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
            var line = tMgr.line1Number.trimStart('+')

            line =line.substring(line.length-10,line.length)
            if(line.isEmpty()) {
                FirebaseCrashlytics.getInstance().recordException(Exception("numero no valido"))

                ""

            }
            else {
                GlobalSettings.setCurrentPhone(line, lada)
                "$lada$line";
            }
        }catch (e:Exception){
            FirebaseCrashlytics.getInstance().recordException(Exception(e))

            ""
        }

    }
}
