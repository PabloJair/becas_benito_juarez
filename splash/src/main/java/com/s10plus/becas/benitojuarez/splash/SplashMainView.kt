package com.s10plus.becas.benitojuarez.splash

import android.os.AsyncTask
import com.s10plus.becas.benitojuarez.splash.databinding.ActivitySplashBinding
import com.s10plus.core_application.GlobalSettings
import com.s10plus.core_application.base_ui.BaseActivity
import com.s10plus.core_application.navigation.AppNavigation
import com.s10plus.core_application.utils.Constans

class SplashMainView:BaseActivity<ActivitySplashBinding>(R.layout.activity_splash) {
    override fun setupView() {
        AsyncTask.execute {

            Thread.sleep(5000)
            runOnUiThread {

                if(GlobalSettings.validateSession())
                    startActivity( AppNavigation.openMainView(this).apply {
                        putExtra(Constans.DATA_EXTRAS,GlobalSettings.getUser())

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