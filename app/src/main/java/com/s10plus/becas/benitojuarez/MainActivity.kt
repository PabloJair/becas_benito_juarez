package com.s10plus.becas.benitojuarez


import android.content.Intent
import android.content.IntentFilter
import com.s10plus.becas.benitojuarez.databinding.ActivityMainBinding
import com.s10plus.core_application.IntercepterCallPhone
import com.s10plus.core_application.base_ui.BaseActivity
import com.s10plus.core_application.navigation.AppNavigation
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    override fun setupView() {

        startActivity(AppNavigation.openSplash(this))
        finish()


    }

    override fun setupViewModel() {
    }

    override fun setupObserver() {
    }

    override fun init() {
    }


}
