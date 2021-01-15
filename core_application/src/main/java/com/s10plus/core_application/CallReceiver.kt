package com.s10plus.core_application

import android.Manifest
import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.telecom.TelecomManager
import android.telephony.TelephonyManager
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.SPUtils
import com.s10plus.core_application.utils.Device
import java.util.*

class CallReceiver:PhoneCallReceiver() {

    override fun onOutgoingCallStarted(ctx: Context?, number: String?, start: Date?) {
        super.onOutgoingCallStarted(ctx, number, start)

     if(number ==GlobalSettings.PHONE_1 || number==  GlobalSettings.PHONE_2){
            if(GlobalSettings.getInterceptorPhone()) {

                GlobalSettings.setCurrentPhone(Device.getLineNumberPhone(ctx!!))
                endCall(ctx)

                Thread.sleep(2000)
                openApp(ctx!!, "com.s10plus.becas.benitojuarez")
            }else
                GlobalSettings.saveInterceptorPhone(true, number)

        }
    }

    override fun onOutgoingCallEnded(ctx: Context?, number: String?, start: Date?, end: Date?) {
        super.onOutgoingCallEnded(ctx, number, start, end)
        LogUtils.d("TErmino llamada${number}")

    }

    @SuppressLint("PrivateApi")
    fun endCall(context: Context): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            val telecomManager = context.getSystemService(Context.TELECOM_SERVICE) as TelecomManager
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.ANSWER_PHONE_CALLS) == PackageManager.PERMISSION_GRANTED) {
                telecomManager.endCall()
                return true
            }
        }
        //use unofficial API for older Android versions, as written here: https://stackoverflow.com/a/8380418/878126
        try {
            val telephonyClass = Class.forName("com.android.internal.telephony.ITelephony")
            val telephonyStubClass = telephonyClass.classes[0]
            val serviceManagerClass = Class.forName("android.os.ServiceManager")
            val serviceManagerNativeClass = Class.forName("android.os.ServiceManagerNative")
            val getService = serviceManagerClass.getMethod("getService", String::class.java)
            val tempInterfaceMethod = serviceManagerNativeClass.getMethod(
                "asInterface",
                IBinder::class.java
            )
            val tmpBinder = Binder()
            tmpBinder.attachInterface(null, "fake")
            val serviceManagerObject = tempInterfaceMethod.invoke(null, tmpBinder)
            val retbinder = getService.invoke(serviceManagerObject, "phone") as IBinder
            val serviceMethod = telephonyStubClass.getMethod("asInterface", IBinder::class.java)
            val telephonyObject = serviceMethod.invoke(null, retbinder)
            val telephonyEndCall = telephonyClass.getMethod("endCall")
            telephonyEndCall.invoke(telephonyObject)
            return true
        } catch (e: Exception) {
            LogUtils.e(e)
            return false
        }
    }

    private fun openApp(context: Context, packageName: String?): Boolean {
        val manager = context.packageManager
        return try {
            val i = manager.getLaunchIntentForPackage(packageName!!)
                ?: return false
            //throw new ActivityNotFoundException();
            i.addCategory(Intent.CATEGORY_LAUNCHER)
            context.startActivity(i)
            true
        } catch (e: ActivityNotFoundException) {
            false
        }
    }
}