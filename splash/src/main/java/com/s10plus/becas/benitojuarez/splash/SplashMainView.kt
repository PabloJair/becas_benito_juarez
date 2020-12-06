package com.s10plus.becas.benitojuarez.splash

import android.Manifest
import android.Manifest.permission.*
import android.content.pm.PackageManager
import android.os.AsyncTask
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.blankj.utilcode.util.LogUtils

import com.s10plus.becas.benitojuarez.splash.databinding.ActivitySplashBinding
import com.s10plus.core_application.GlobalSettings
import com.s10plus.core_application.base_ui.BaseActivity
import com.s10plus.core_application.navigation.AppNavigation
import com.s10plus.core_application.ui.dialog.TypeDialog
import com.s10plus.core_application.utils.Constans
import com.tbruyelle.rxpermissions3.RxPermissions

class SplashMainView:BaseActivity<ActivitySplashBinding>(R.layout.activity_splash) {

    @RequiresApi(Build.VERSION_CODES.M)
    var rxPermissions =RxPermissions(this)
    var countPermission=2
    var showMessage = true

    override fun setupView() {


        rxPermissions.setLogging(true)
        GlobalSettings.saveInterceptorPhone(false)
        if(
            rxPermissions.isGranted(PROCESS_OUTGOING_CALLS)
            && rxPermissions.isGranted(READ_PHONE_STATE)
            && rxPermissions.isGranted(CALL_PHONE)
            && rxPermissions.isGranted(ANSWER_PHONE_CALLS)){
            executeApp()
            return
        }

        validatePermission()


    }

    private fun validatePermission(){
        if(countPermission==0){
            executeApp()
            return
        }

        var permissionValidate =0

        rxPermissions.request(
            READ_PHONE_STATE,
            ANSWER_PHONE_CALLS,
            PROCESS_OUTGOING_CALLS,
            CALL_PHONE

        ).subscribe {
            if (!it){
                if(showMessage) {
                    showDialog(TypeDialog.ERROR,
                        title = "Permisos requeridos",
                        "¡Es importante aceptar los permisos requerido para tener una mejor experiencia en tu aplicación!",
                        null, { modal->
                            countPermission--
                            showMessage = false
                            modal.dismiss()
                            validatePermission()

                        })
                }
            }else{
                permissionValidate++
                if(permissionValidate ==1){
                    executeApp()
                }
            }

        }
    }


    private fun executeApp(){
        AsyncTask.execute {

            Thread.sleep(5000)
            runOnUiThread {

                if(GlobalSettings.validateSession())
                    startActivity(AppNavigation.openMainView(this).apply {
                        putExtra(Constans.DATA_EXTRAS, GlobalSettings.getUser())

                    })
                else startActivity(AppNavigation.openLogin(this))
                finish()
            }

        }
    }


    override fun setupViewModel() {
    }

    override fun setupObserver() {
    }

    override fun init() {
    }
}