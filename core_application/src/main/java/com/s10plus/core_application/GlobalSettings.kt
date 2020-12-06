package com.s10plus.core_application

import android.content.Context
import android.content.SharedPreferences
import com.blankj.utilcode.util.GsonUtils
import com.blankj.utilcode.util.SPUtils
import com.s10plus.core_application.models.UserInformation

object GlobalSettings {


    const val USER="USER"
    const val SP_S10PLUS="SP_S10PLUS"
    const val SP_INTERCEPTER_PHONE="SP_INTERCEPTER_PHONE"
    const val SP_NUMBER_PHONE="SP_NUMBER_PHONE"
    const val PHONE_1="5511620300"
    const val PHONE_2="018005005050"


    private var userInformation:UserInformation?=null

    fun setSession( userInformation: UserInformation){
        SPUtils.getInstance(SP_S10PLUS,Context.MODE_PRIVATE).put(USER,GsonUtils.toJson(userInformation))


    }


    fun getNumberPhone():String{
        val np = SPUtils.getInstance(SP_S10PLUS,Context.MODE_PRIVATE).getString(SP_NUMBER_PHONE)
        return if(np.isEmpty()) PHONE_1 else np

    }


    fun saveInterceptorPhone(isInterceptor:Boolean,phoneNumber:String=""){
        SPUtils.getInstance(SP_S10PLUS,Context.MODE_PRIVATE)
            .put(SP_INTERCEPTER_PHONE,isInterceptor)

        SPUtils.getInstance(SP_S10PLUS,Context.MODE_PRIVATE)
            .put(SP_NUMBER_PHONE,phoneNumber)

    }

    fun getInterceptorPhone():Boolean{
        return SPUtils.getInstance(SP_S10PLUS,Context.MODE_PRIVATE)
            .getBoolean(SP_INTERCEPTER_PHONE)

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