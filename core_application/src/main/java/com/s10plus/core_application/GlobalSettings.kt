package com.s10plus.core_application

import android.content.Context
import android.content.SharedPreferences
import com.blankj.utilcode.util.GsonUtils
import com.blankj.utilcode.util.SPUtils
import com.s10plus.core_application.models.UserInformation

object GlobalSettings {


    var saveReceiverCall = true ;
    const val USER="USER"
    const val SP_S10PLUS="SP_S10PLUS-AMAD"
    const val TOKEN="TOKEN"
    const val SP_INTERCEPTER_PHONE="SP_INTERCEPTER_PHONE"
    const val SP_NUMBER_PHONE="SP_NUMBER_PHONE"
    const val PHONE_1="5511620300"
    const val PHONE_2="8005005050"
    const val CURRENT_PHONE="CURRENT_PHONE"
     var lat:Double=0.toDouble()
     var  lng:Double=0.0

    private var token:String?=null
    private var current_phone:String?=null

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
    fun getToken():String=
        if(token==null) {
            token = SPUtils.getInstance(SP_S10PLUS, Context.MODE_PRIVATE).getString(TOKEN)
            token!!
        }else token!!


    fun getCurrentPhone(quitExtension:Boolean = false):String=
        if(current_phone==null|| current_phone.isNullOrEmpty()){
            current_phone = SPUtils.getInstance(SP_S10PLUS, Context.MODE_PRIVATE).getString(CURRENT_PHONE)
            if(quitExtension){
            current_phone?.replace("+521","")!!
            }else
                current_phone!!
        }else {
            if(quitExtension){
                current_phone?.replace("+521","")!!
            }else
                current_phone!!
        }


    fun setCurrentPhone( phone:String,lada:String="+521"){
        if(phone.isNullOrEmpty())
            return
        current_phone = if (phone.isNotEmpty()) {
            SPUtils.getInstance(SP_S10PLUS, Context.MODE_PRIVATE).put(CURRENT_PHONE, "$lada$phone")
            "$lada$phone"
        }else {
            SPUtils.getInstance(SP_S10PLUS, Context.MODE_PRIVATE).put(CURRENT_PHONE, "")
            ""
        }

    }
    fun setToken( token:String){
        SPUtils.getInstance(SP_S10PLUS,Context.MODE_PRIVATE).put(TOKEN,token)
        this.token = token

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