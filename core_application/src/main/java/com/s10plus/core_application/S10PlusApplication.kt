package com.s10plus.core_application

import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ProcessLifecycleOwner
import com.s10plus.core_application.di.applicationModule
import com.s10plus.core_application.di.databaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class S10PlusApplication: Application(),LifecycleObserver {
    override fun onCreate() {
        super.onCreate()

        currentApplication = this

        ProcessLifecycleOwner.get().lifecycle.addObserver(this)

        startKoin {
            // declare used Android context
            androidContext(this@S10PlusApplication)
            // declare modules
            modules(databaseModule)
            modules(applicationModule)

        }





    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onAppDestroyed(){
        GlobalSettings.saveInterceptorPhone(true,GlobalSettings.getNumberPhone())
        Toast.makeText(this, "Destruyo la app", Toast.LENGTH_LONG).show()

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onAppStoped(){
        GlobalSettings.saveInterceptorPhone(true,GlobalSettings.getNumberPhone())
        //Toast.makeText(this, "Stop la app", Toast.LENGTH_LONG).show()

    }


    companion object{

       lateinit var currentApplication :Context
    }
}