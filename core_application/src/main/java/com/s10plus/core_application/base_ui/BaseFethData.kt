package com.s10plus.core_application.base_ui

sealed class BaseFethData {


    data class Loader(var isShow:Boolean):BaseFethData()
    data class Error(var message:String):BaseFethData()
    data class Success(var data:Any):BaseFethData()
}