package com.s10plus.core_application

import android.content.Context
import android.content.SharedPreferences
import com.blankj.utilcode.util.GsonUtils
import com.blankj.utilcode.util.SPUtils
import com.s10plus.core_application.models.UserInformation

object GlobalSettings {


    const val USER="USER"
    const val SP_S10PLUS="SP_S10PLUS"

    private var userInformation:UserInformation?=null

    fun setSession( userInformation: UserInformation){
        SPUtils.getInstance(SP_S10PLUS,Context.MODE_PRIVATE).put(USER,GsonUtils.toJson(userInformation))

    }

    fun getUser():UserInformation=
        if(userInformation==null) {
            SPUtils.getInstance(SP_S10PLUS, Context.MODE_PRIVATE).getString(USER).let {

                GsonUtils.fromJson(it, UserInformation::class.java)

            }
        }else userInformation!!

 fun validateSession():Boolean{

     val json=SPUtils.getInstance(SP_S10PLUS, Context.MODE_PRIVATE).getString(USER)

     return if(json.isNullOrEmpty())
         false
     else {
         getUser()
         true

     }

 }

    fun closeSession(){
        SPUtils.getInstance(SP_S10PLUS,Context.MODE_PRIVATE).apply {

            remove(USER)
        }

    }
}