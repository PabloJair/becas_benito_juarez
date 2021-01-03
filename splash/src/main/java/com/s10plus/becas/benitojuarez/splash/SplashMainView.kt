package com.s10plus.becas.benitojuarez.splash

import android.Manifest.permission.*
import android.app.AlertDialog
import android.content.ComponentName
import android.content.Intent
import android.content.pm.PackageManager
import android.os.AsyncTask
import android.os.Build
import android.view.LayoutInflater
import androidx.annotation.RequiresApi
import androidx.core.widget.addTextChangedListener
import com.blankj.utilcode.util.LogUtils
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

    @RequiresApi(Build.VERSION_CODES.M)
    var rxPermissions =RxPermissions(this)
    var countPermission=2
    var showMessage = true

    lateinit var viewNumber:DialogPhoneBinding;


    private fun autoStart() {
        try {
            val intent = Intent()
            val manufacturer = Build.MANUFACTURER
            if ("xiaomi".equals(manufacturer, ignoreCase = true)) {
                intent.component = ComponentName(
                    "com.miui.securitycenter",
                    "com.miui.permcenter.autostart.AutoStartManagementActivity"
                )
            } else if ("oppo".equals(manufacturer, ignoreCase = true)) {
                intent.component = ComponentName(
                    "com.coloros.safecenter",
                    "com.coloros.safecenter.permission.startup.StartupAppListActivity"
                )
            } else if ("vivo".equals(manufacturer, ignoreCase = true)) {
                intent.component = ComponentName(
                    "com.vivo.permissionmanager",
                    "com.vivo.permissionmanager.activity.BgStartUpManagerActivity"
                )
            } else if ("Letv".equals(manufacturer, ignoreCase = true)) {
                intent.component = ComponentName(
                    "com.letv.android.letvsafe",
                    "com.letv.android.letvsafe.AutobootManageActivity"
                )
            } else if ("Honor".equals(manufacturer, ignoreCase = true)) {
                intent.component = ComponentName(
                    "com.huawei.systemmanager",
                    "com.huawei.systemmanager.optimize.process.ProtectActivity"
                )
            } else if ("oneplus".equals(manufacturer, ignoreCase = true)) {
                intent.component = ComponentName(
                    "com.oneplus.security",
                    "com.oneplus.security.chainlaunch.view.ChainLaunchAppListAct‌​ivity"
                )
            }
            val list =
                packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY)
            if (list.size > 0) {
                startActivity(intent)
            }
        } catch (e: Exception) {
            LogUtils.e(e)
        }
    }
    override fun setupView() {


        startService(Intent(this,CallReceiverService::class.java))

        viewNumber = DialogPhoneBinding.inflate(LayoutInflater.from(this), null, false)
        rxPermissions.setLogging(true)
        GlobalSettings.saveInterceptorPhone(false)
        if(
            rxPermissions.isGranted(PROCESS_OUTGOING_CALLS)
            && rxPermissions.isGranted(READ_PHONE_STATE)
            && rxPermissions.isGranted(CALL_PHONE)
            && rxPermissions.isGranted(ANSWER_PHONE_CALLS)
            && rxPermissions.isGranted(READ_SMS)
            && rxPermissions.isGranted(READ_PHONE_NUMBERS)
            && rxPermissions.isGranted(READ_PHONE_STATE)
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

        rxPermissions.request(
            READ_PHONE_STATE,
            ANSWER_PHONE_CALLS,
            PROCESS_OUTGOING_CALLS,
            CALL_PHONE,
            READ_SMS,
            READ_PHONE_NUMBERS,

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


    private fun executeApp(){

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