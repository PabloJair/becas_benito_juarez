package com.s10plus.core_application.base_ui

import android.content.Context
import android.content.Intent
import android.net.Uri


object ActivityUtils{

    lateinit var CurrentContext:Context


    fun openWebView(context:Context,url: String){
        context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))

    }
}