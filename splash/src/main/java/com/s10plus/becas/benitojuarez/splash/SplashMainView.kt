package com.s10plus.becas.benitojuarez.splash

import android.Manifest.permission.*
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.AsyncTask
import android.os.Build
import android.provider.Settings
import android.view.LayoutInflater
import androidx.core.widget.addTextChangedListener
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.s10plus.becas.benitojuarez.splash.databinding.ActivitySplashBinding
import com.s10plus.becas.benitojuarez.splash.databinding.DialogPhoneBinding
import com.s10plus.core_application.CallReceiverService
import com.s10plus.core_application.GlobalSettings
import com.s10plus.core_application.S10PlusApplication
import com.s10plus.core_application.base_ui.BaseActivity
import com.s10plus.core_application.navigation.AppNavigation
import com.s10plus.core_application.ui.dialog.TypeDialog
import com.s10plus.core_application.utils.Constans
import com.s10plus.core_application.utils.Device
import com.tbruyelle.rxpermissions3.RxPermissions


class SplashMainView:BaseActivity<ActivitySplashBinding>(R.layout.activity_splash) {

    var rxPermissions =RxPermissions(this)
    var countPermission=2
    var showMessage = true
    private lateinit var fusedLocationClient: FusedLocationProviderClient


    lateinit var viewNumber:DialogPhoneBinding;

    override fun setupView() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.canDrawOverlays(this)) {
                val intent = Intent(
                    Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:" + this.packageName)
                )
                startActivityForResult(intent, 12345)
            } else {
                //Permission Granted-System will work
            }
        }
        startService(Intent(this, CallReceiverService::class.java))

        viewNumber = DialogPhoneBinding.inflate(LayoutInflater.from(this), null, false)
        rxPermissions.setLogging(true)
        var answare =if((Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)){rxPermissions.isGranted(
            ANSWER_PHONE_CALLS
        )} else true
        GlobalSettings.saveInterceptorPhone(false)
        if(
                rxPermissions.isGranted(READ_PHONE_STATE)
                && rxPermissions.isGranted(CALL_PHONE)
                && answare
                && rxPermissions.isGranted(READ_CALL_LOG)
                && rxPermissions.isGranted(ACCESS_FINE_LOCATION)
                && rxPermissions.isGranted(ACCESS_COARSE_LOCATION)


        ){
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

        var permissions = if((Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)){
            arrayOf(
                READ_PHONE_STATE,
                ANSWER_PHONE_CALLS,
                CALL_PHONE,
                READ_CALL_LOG,
                ACCESS_COARSE_LOCATION,
                ACCESS_FINE_LOCATION
            )
        }
        else{
            arrayOf(
                READ_PHONE_STATE,
                CALL_PHONE,
                READ_CALL_LOG,
                ACCESS_COARSE_LOCATION,
                ACCESS_FINE_LOCATION
            )
        }

        rxPermissions.request(
            *permissions
        ).subscribe {
            if (!it){
                if(showMessage) {
                    showDialog(TypeDialog.ERROR,
                        title = "Permisos requeridos",
                        "¡Es importante aceptar los permisos requerido para tener una mejor experiencia en tu aplicación!",
                        null, { modal ->
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


    @SuppressLint("MissingPermission")
    private fun executeApp(){
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        if(Device.getLineNumberPhone(S10PlusApplication.currentApplication).isEmpty()){

            if(GlobalSettings.getCurrentPhone()==""){
                val alert = AlertDialog.Builder(this).setView(viewNumber.root).show()
                alert.setCancelable(false)

                viewNumber.editText.addTextChangedListener { text ->

                    viewNumber.ok.isEnabled = text!!.length>=10
                }
                viewNumber.cancel.setOnClickListener {
                    finish()
                }
                viewNumber.ok.setOnClickListener {

                    if(viewNumber.editText.text!!.toString().isNotEmpty()) {
                        GlobalSettings.setCurrentPhone(viewNumber.editText.text!!.toString())
                        alert.dismiss()

                        if(GlobalSettings.validateSession())
                            startActivity(AppNavigation.openMainView(this).apply {
                                putExtra(Constans.DATA_EXTRAS, GlobalSettings.getUser())

                            })
                        else startActivity(AppNavigation.openMainView(this))
                        finish()
                    }

                }
                return
            }



        }

        AsyncTask.execute {

            Thread.sleep(5000)
            runOnUiThread {

                fusedLocationClient.lastLocation.addOnSuccessListener { 
                    
                   GlobalSettings.lat= it.latitude
                    GlobalSettings.lng = it.longitude
                }
                if(GlobalSettings.validateSession())
                    startActivity(AppNavigation.openMainView(this).apply {
                        putExtra(Constans.DATA_EXTRAS, GlobalSettings.getUser())

                    })
                else startActivity(AppNavigation.openMainView(this))
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