package com.s10plus.core_application

import android.Manifest
import android.annotation.SuppressLint
import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.telecom.TelecomManager
import android.telephony.PhoneStateListener
import android.telephony.TelephonyManager
import android.widget.Toast
import androidx.core.content.ContextCompat


class IntercepterCallPhone : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val tm = context!!.getSystemService(Service.TELEPHONY_SERVICE) as TelephonyManager
        tm.listen(object : PhoneStateListener() {

            override fun onCallStateChanged(state: Int, phoneNumber: String?) {
                when(state){
                    TelephonyManager.CALL_STATE_RINGING->{}
                    TelephonyManager.CALL_STATE_IDLE->{}
                    TelephonyManager.CALL_STATE_OFFHOOK->{
                        println("call Activity off hook");
                        //Toast.makeText(context, "Numero detectado:${phoneNumber}", Toast.LENGTH_LONG).show()


                    }

                }
            }
        },PhoneStateListener.LISTEN_CALL_STATE)

        if (tm.callState == TelephonyManager.CALL_STATE_OFFHOOK) {
            val number = intent!!.getStringExtra(Intent.EXTRA_PHONE_NUMBER)


            Toast.makeText(context, "Numero detectado:${number}", Toast.LENGTH_LONG).show()
            //openApp(context,"com.s10plus.demo_becas")

        }
    }

    @SuppressLint("PrivateApi")
    fun endCall(context: Context): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            val telecomManager = context.getSystemService(Context.TELECOM_SERVICE) as TelecomManager
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.ANSWER_PHONE_CALLS) == PackageManager.PERMISSION_GRANTED) {
                //telecomManager.endCall()
                return true
            }
            return false
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
            e.printStackTrace()
            return false
        }
    }
}