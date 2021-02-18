package com.s10plus.becas.feature_main

import android.app.AlertDialog
import android.media.MediaPlayer
import android.view.LayoutInflater
import android.widget.TextView
import androidx.lifecycle.Observer
import com.s10plus.becas.feature_main.databinding.ActivityMainViewBinding
import com.s10plus.becas.feature_main.di.injectFeature
import com.s10plus.becas.feature_main.view_model.MainViewModel
import com.s10plus.core_application.base_ui.BaseActivity
import com.s10plus.core_application.base_ui.BaseFethData
import com.s10plus.core_application.models.UserInformation
import com.s10plus.core_application.utils.Constans
import com.s10plus.feature_home.FragmentMenu
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity: BaseActivity<ActivityMainViewBinding>(R.layout.activity_main_view)  {
    private val viewModel: MainViewModel by viewModel()


    override fun setupInjection() {
        injectFeature()
    }

    override fun setupView() {

        val mp = MediaPlayer.create(this, R.raw.intro)
        mp.start()


        viewModel.load()


    }

    override fun setupViewModel() {
    }

    override fun setupObserver() {
        viewModel.liveDataManager.observe(this, {
            when (it) {
                is BaseFethData.Error -> {

                    //var view =LayoutInflater.from(this).inflate(R.layout.error_dialog,null)
                    //view.findViewById<TextView>(R.id.error).text = it.message
                    //AlertDialog.Builder(this).setView(view).show()
                }
                is BaseFethData.Success -> {

                    //var view =LayoutInflater.from(this).inflate(R.layout.error_dialog,null)
                    //view.findViewById<TextView>(R.id.error).text = it.data as String
                    //AlertDialog.Builder(this).setView(view).show()
                }
                is BaseFethData.Loader -> {
                }

            }


        })
    }



    override fun init() {
        val userInformation = intent.getParcelableExtra<UserInformation>(Constans.DATA_EXTRAS)

      //  viewModel.getModules(userInformation.idUser, userInformation.idCompany)

        assignFragment(R.id.containerFragment, FragmentMenu.newInstanceMainMenu(this))

    }

    override var layoutIdFragment: Int = 0
        get() = R.id.containerFragment
}