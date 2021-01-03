package com.s10plus.becas.benitojuarez.feature_login.view_model

import com.s10plus.core_application.GlobalSettings
import com.s10plus.core_application.base_ui.BaseViewModel
import com.s10plus.becas.benitojuarez.feature_login.models.LoginRequest
import io.reactivex.functions.Consumer

class LoginViewModel:BaseViewModel(){

    private var service = serverRetrofit.getService(LoginService::class.java)

}