package com.s10plus.core_application

import android.app.Application
import android.content.Context
import android.util.Log
import android.widget.Toast
import com.blankj.utilcode.util.LogUtils
import com.facebook.FacebookActivity
import com.facebook.FacebookSdk
/*import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.messaging.FirebaseMessaging*/
import com.s10plus.core_application.di.applicationModule
import com.s10plus.core_application.di.databaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class S10PlusApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        currentApplication = this

        startKoin {
            // declare used Android context
            androidContext(this@S10PlusApplication)
            // declare modules
            modules(databaseModule)
            modules(applicationModule)

        }




    }

    companion object{

       lateinit var currentApplication :Context
    }
}