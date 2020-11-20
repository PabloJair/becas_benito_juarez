package com.s10plus.becas.benitojuarez.feature_login

import android.view.View
import androidx.lifecycle.Observer
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.s10plus.becas.benitojuarez.feature_login.databinding.ActivityLoginViewBinding
import com.s10plus.core_application.base_ui.BaseActivity
import com.s10plus.core_application.base_ui.BaseFethData
import com.s10plus.core_application.models.BaseModel
import com.s10plus.core_application.models.UserInformation

import com.s10plus.core_application.navigation.AppNavigation
import com.s10plus.core_application.ui.dialog.TypeDialog
import com.s10plus.core_application.utils.Constans.Companion.DATA_EXTRAS
import com.s10plus.becas.benitojuarez.feature_login.di.injectFeature
import com.s10plus.becas.benitojuarez.feature_login.view_model.LoginViewModel
import com.s10plus.core_application.models.ItemMenu
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginView:BaseActivity<ActivityLoginViewBinding>(R.layout.activity_login_view),View.OnClickListener {

    private val viewModel: LoginViewModel by viewModel()
    lateinit var callbackManager: CallbackManager

    private var email :String=""

    override fun setupView() {



        binding.login.setOnClickListener {

            val info =UserInformation(email ="pablo.angeles@s10plus.com",modules = arrayListOf(
                ItemMenu("",1,text_item = "Menu Principal"),
                ItemMenu("",2,"Nosotros"),
                ItemMenu("",3,"Trasparencia"),
                ItemMenu("",4,"Controlaría Social")
            ))

            val intent = AppNavigation.openMainView(this).apply {
                putExtra(DATA_EXTRAS,info)

            }

            startActivity(intent)
            finish()
        }

        callbackManager = CallbackManager.Factory.create()

        binding.facebookButton.registerCallback(callbackManager, object : FacebookCallback<LoginResult>{
            override fun onSuccess(result: LoginResult?) {

            }

            override fun onCancel() {
            }

            override fun onError(error: FacebookException?) {
            }


        })


    }

    override fun setupViewModel() {

    }

    override fun setupObserver() {
        viewModel.liveDataManager.observe(this, Observer {
            when(it){
                is BaseFethData.Error->{
                    showDialog(TypeDialog.ERROR,"¡UPS!",it.message,ok = { dialog->dialog.dismiss()})


                }
                is BaseFethData.Success->{
                        when(it.data){

                            is UserInformation->{
                                val info =(it.data as UserInformation)

                                if(info!=null) {

                                    val intent = AppNavigation.openMainView(this).apply {
                                        putExtra(DATA_EXTRAS,info)

                                    }

                                    startActivity(intent)
                                    finish()

                                }
                            }
                            is BaseModel<*>->{
                                var dialogSendRecoveryPassword: DialogSendRecoveryPassword =
                                    DialogSendRecoveryPassword()
                                var info = it.data as BaseModel<String>

                                if(info.data=="1") {
                                    showDialog(
                                        TypeDialog.SUCCESS,
                                        "¡Verifica tu correo!",
                                        info.message as String,
                                        ok = { dialog ->

                                            var dialogSendRecoveryPassword: DialogSendRecoveryPassword =
                                                DialogSendRecoveryPassword()
                                            dialogSendRecoveryPassword.show(
                                                supportFragmentManager,
                                                "TAG"
                                            )
                                            dialogSendRecoveryPassword.onClick =
                                                { cod, password, dialog ->

                                                    viewModel.sendPassword(email, password, cod)
                                                    dialog.dismiss()


                                                }


                                        })
                                }else if(info.data =="2"){


                                    showDialog(TypeDialog.SUCCESS,"Correcto!!",info.message as String,ok={

                                        ModalGenericDialog->
                                        ModalGenericDialog.dismiss()

                                    })
                                }



                            }
                        }

                }
                is BaseFethData.Loader->{
                    if(it.isShow) showLoader() else hideLoader()
                }

            }
        })

    }

    override fun init() {

    }


    private fun validateForm():Boolean{

        var validate = true
        if(binding.correo.editText!!.text.toString().isNullOrEmpty()){
            binding.correo.error="Ingresa tu correo"
            validate = false
        }
        if(binding.password.editText!!.text.toString().isNullOrEmpty()){
            binding.correo.error="Ingresa tu password"
            validate = false
        }

        return validate

    }

    override fun setupInjection() {
        injectFeature()

    }

    override fun onClick(v: View?) {

        when(v){


        }

    }
}