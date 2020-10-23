package com.s10plus.becas.benitojuarez.feature_login.view_model

import com.s10plus.core_application.GlobalSettings
import com.s10plus.core_application.base_ui.BaseViewModel
import com.s10plus.becas.benitojuarez.feature_login.models.LoginRequest
import io.reactivex.functions.Consumer

class LoginViewModel:BaseViewModel(){

    private var service = serverRetrofit.getService(LoginService::class.java)


    fun login(password:String, email:String){




        loader.value = true
        setupSubscribe(service.login(LoginRequest(email, password)),
            Consumer {
                loader.value = false

                if(it.code ==200)
                success.value = it.data
                else
                    error.value=it.message

                GlobalSettings.setSession(userInformation = it.data)


            },
            Consumer {
                loader.value = false
                error.value="Las credenciales son invalidas"

            })

    }
    fun recoveryPassword(email:String){




        loader.value = true
        setupSubscribe(service.recoveryPassword(hashMapOf(Pair<String,String>("email",email))),
            Consumer {
                loader.value = false

                if(it.code ==200)
                    success.value = it
                else
                    error.value=it.message




            },
            Consumer {
                loader.value = false
                error.value="Error al cargar la información"

            })

    }


    fun sendPassword(email:String,password: String,token:String){

        loader.value = true
        setupSubscribe(service.sendNewPassword(hashMapOf(Pair<String,String>("email",email),
            Pair("password",password),Pair("token",token))),
            Consumer {
                loader.value = false

                if(it.code ==200)
                    success.value = it
                else
                    error.value=it.message



            },
            Consumer {
                loader.value = false
                error.value="Las credenciales son invalidas"

            })

    }
}