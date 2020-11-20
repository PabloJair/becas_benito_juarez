package com.s10plus.becas.feature_main

import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.blankj.utilcode.util.LogUtils
import com.s10plus.becas.feature_main.databinding.ActivityMainViewBinding
import com.s10plus.becas.feature_main.di.injectFeature
import com.s10plus.becas.feature_main.menu.MenuAdapter
import com.s10plus.becas.feature_main.view_model.MainViewModel
import com.s10plus.core_application.GlobalSettings
import com.s10plus.core_application.base_ui.BaseActivity
import com.s10plus.core_application.base_ui.BaseFethData
import com.s10plus.core_application.models.ItemMenu
import com.s10plus.core_application.models.UserInformation
import com.s10plus.core_application.navigation.AppNavigation
import com.s10plus.core_application.utils.Constans
import com.s10plus.feature_home.FragmentHome
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity: BaseActivity<ActivityMainViewBinding>(R.layout.activity_main_view)  {

    private lateinit var adapterPrimaryMenu: MenuAdapter;
    private lateinit var adapterSecondaryMenu: MenuAdapter;

    private val viewModel: MainViewModel by viewModel()


    override fun setupInjection() {
        injectFeature()
    }

    override fun setupView() {
        setSupportActionBar(binding.appBarLayout.toolbar)
        setupAppBar(binding.appBarLayout.toolbar,"Inicio")

        val actionBarDrawerToggle = ActionBarDrawerToggle(
            this,
            binding.drawerLayout,
            binding.appBarLayout.toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )

        binding.drawerLayout.addDrawerListener(actionBarDrawerToggle)

        supportActionBar!!.setHomeButtonEnabled(true);
        supportActionBar!!.setDisplayHomeAsUpEnabled(true);
        actionBarDrawerToggle.syncState();

        adapterPrimaryMenu = MenuAdapter()
        adapterPrimaryMenu.onClickItem = { item, view ->
            run {

                binding.drawerLayout.closeDrawers()
                when (item.id_item) {

                    1 -> {
                        assignFragment(R.id.containerFragment, FragmentHome())

                    }
                    3->{

                    }
                    2 -> {

                    }

                    else -> {
                    }
                }
            }
        }

        binding.recyclerViewMenu.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recyclerViewMenu.adapter = adapterPrimaryMenu
        binding.recyclerSecundaryMenu.apply {

            layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, true)

            adapterSecondaryMenu = MenuAdapter()
            adapterSecondaryMenu.items = getDefaultMenu()
            adapterSecondaryMenu.onClickItem = onClickSecundaryMenu

            adapter = adapterSecondaryMenu
        }


    }


    var onClickSecundaryMenu = { item: ItemMenu, view: ViewDataBinding ->
        run {

            binding.drawerLayout.closeDrawers()
            when (item.id_item) {

                99 -> {
                    GlobalSettings.closeSession()
                    finish()

                    startActivity(AppNavigation.openLogin(this@MainActivity))
                }
                else -> {

                }
            }
        }
    }

    private fun getDefaultMenu(): List<ItemMenu> {

        return arrayListOf(
            ItemMenu("ic_close", 99, "Configuración")


        )

    }


    override fun setupViewModel() {
    }

    override fun setupObserver() {
        viewModel.liveDataManager.observe(this, Observer {
            when (it) {
                is BaseFethData.Error -> {

                }
                is BaseFethData.Success -> {

                    val info = (it.data as UserInformation)
                    setupData(info)
                }
                is BaseFethData.Loader -> {

                    if (it.isShow) showLoader() else hideLoader()
                }

            }


        })
    }

    private fun setupData(userInformation: UserInformation) {
        binding.name.text = userInformation.name
        binding.email.text = userInformation.email
        adapterPrimaryMenu.items = userInformation.modules

    }

    override fun init() {
        val userInformation = intent.getParcelableExtra<UserInformation>(Constans.DATA_EXTRAS)

        viewModel.getModules(userInformation.idUser, userInformation.idCompany)

        assignFragment(R.id.containerFragment, FragmentHome())

    }
}