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
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import com.blankj.utilcode.util.KeyboardUtils
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.material.snackbar.Snackbar
import com.s10plus.becas.benitojuarez.splash.databinding.ActivitySplashBinding
import com.s10plus.becas.benitojuarez.splash.databinding.DialogPhoneBinding
import com.s10plus.becas.benitojuarez.splash.ui.CustomSnackbar
import com.s10plus.core_application.CallReceiverService
import com.s10plus.core_application.GlobalSettings
import com.s10plus.core_application.S10PlusApplication
import com.s10plus.core_application.base_ui.BaseActivity
import com.s10plus.core_application.navigation.AppNavigation
import com.s10plus.core_application.ui.dialog.TypeDialog
import com.s10plus.core_application.utils.Constans
import com.s10plus.core_application.utils.Device
import com.tbruyelle.rxpermissions3.RxPermissions
import kotlinx.android.synthetic.main.custom_snackbar_model_one.view.*


class SplashMainView:BaseActivity<ActivitySplashBinding>(R.layout.activity_splash) {

    var rxPermissions =RxPermissions(this)
    var countPermission=2
    var showMessage = true
    private lateinit var fusedLocationClient: FusedLocationProviderClient


    lateinit var viewNumber:DialogPhoneBinding;

    override fun setupView() {

       /* if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.canDrawOverlays(this)) {
                val intent = Intent(
                    Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:" + this.packageName)
                )
                startActivityForResult(intent, 12345)
            } else {
                //Permission Granted-System will work
            }
        }*/
        startService(Intent(this, CallReceiverService::class.java))

        viewNumber = DialogPhoneBinding.inflate(LayoutInflater.from(this), null, false)
        rxPermissions.setLogging(true)

        GlobalSettings.saveInterceptorPhone(false)
        if(         rxPermissions.isGranted(ACCESS_FINE_LOCATION)
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
                ACCESS_COARSE_LOCATION,
                ACCESS_FINE_LOCATION,
            )
        }
        else{
            arrayOf(
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



            if(getNumber()) {
            AsyncTask.execute {

                Thread.sleep(3000)
                runOnUiThread {

                    fusedLocationClient.lastLocation.addOnSuccessListener {

                        GlobalSettings.lat = it?.latitude ?: 0.toDouble()
                        GlobalSettings.lng = it?.longitude ?: 0.toDouble()
                    }
                    goToHome()
                }

            }
        }
    }

    fun goToHome(){

        if(GlobalSettings.validateSession())
            startActivity(AppNavigation.openMainView(this).apply {
                putExtra(Constans.DATA_EXTRAS, GlobalSettings.getUser())

            })
        else startActivity(AppNavigation.openMainView(this))
        finish()

    }

    fun getNumber():Boolean{

        if(GlobalSettings.getCurrentPhone()!=""){
            return true
        }
        val alert = AlertDialog.Builder(this).setView(viewNumber.root).show().apply {

            setCancelable(false)

        }
        viewNumber.cancel.setOnClickListener {
            GlobalSettings.setCurrentPhone("0000000000")
            getState()

        }
        viewNumber.ok.setOnClickListener {
            KeyboardUtils.hideSoftInput(viewNumber.root)
            alert.dismiss()

            (viewNumber.root.parent as ViewGroup).removeView(viewNumber.root)

            val snack =CustomSnackbar.make(binding.root,"¿Es correcto tu número telefonico?",viewNumber.editText.text!!.toString(),"Si","No",Snackbar.LENGTH_INDEFINITE)
                .setClickOne {
                   var number =  if(viewNumber.editText.text!!.toString()=="") "0000000000"
                   else viewNumber.editText.text!!.toString()
                    GlobalSettings.setCurrentPhone(number)
                    getState()
                }.setClickTwo {

                    it.dismiss()
                    getNumber()

                }



            snack.show()

        }
        return false
    }

    fun getState (){
        if(GlobalSettings.getState()==null){
            DialogChooseState.dialogState(this,{
                GlobalSettings.setState(it)
                goToHome()
            },{

            })
        }else
            goToHome()


    }

    override fun setupViewModel() {
    }

    override fun setupObserver() {
    }

    override fun init() {
    }
}