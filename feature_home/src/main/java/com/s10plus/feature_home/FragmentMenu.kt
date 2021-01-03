package com.s10plus.feature_home

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.core.view.marginBottom
import androidx.lifecycle.ViewModelProvider
import com.blankj.utilcode.util.GsonUtils
import com.blankj.utilcode.util.LogUtils
import com.s10plus.core_application.GlobalSettings
import com.s10plus.core_application.analytics.AnalyticsViewModel
import com.s10plus.core_application.base_ui.ActivityUtils
import com.s10plus.core_application.base_ui.BaseFragment
import com.s10plus.core_application.base_ui.BaseViewModel
import com.s10plus.core_application.ui.ButtonBlackBecas
import com.s10plus.core_application.ui.ButtonGreenBecas
import com.s10plus.core_application.ui.ButtonGreenLigthBecas
import com.s10plus.core_application.ui.ButtonNSBecas
import com.s10plus.feature_home.MenusCreator.Menu_1
import com.s10plus.feature_home.MenusCreator.menu_2
import com.s10plus.feature_home.MenusCreator.menu_3
import com.s10plus.feature_home.MenusCreator.menu_4
import com.s10plus.feature_home.MenusCreator.menu_5
import com.s10plus.feature_home.databinding.FragmentHomeBinding
import com.s10plus.feature_home.models.DetailsModel
import com.s10plus.feature_home.models.MenuButtonsModel
import com.s10plus.feature_home.models.TypeButton
import com.s10plus.feature_home.models.TypeView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.header_layout.view.*
import kotlin.properties.Delegates
import kotlin.system.exitProcess

class FragmentMenu:BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

     lateinit var menus:Array<MenuButtonsModel>
     var showButtonBack by Delegates.notNull<Boolean>()
    lateinit var headerText: String

    lateinit var analyticsViewModel: AnalyticsViewModel
    override fun setupView() {
        menus = (requireArguments().getParcelableArray(DATA)?:throw Exception("Sin Menus ")) as Array<MenuButtonsModel>
        showButtonBack = requireArguments().getBoolean(BACK_BUTTON,false)
        headerText = requireArguments().getString(HEADER_TEXT,"")

        for (menu in menus){

            when (menu.typeButton) {
                TypeButton.GREEN -> {
                    binding.body.addView(ButtonGreenBecas.instance(requireContext(), menu).apply {
                        text = menu.text
                        setImageResource(menu.idIcon)
                        onClick = {

                           this@FragmentMenu.onClick(menu)
                        }
                    })
                }
                TypeButton.BLACK -> {
                    binding.body.addView(ButtonBlackBecas.instance(requireContext(), menu).apply {
                        text = menu.text
                        setImageResource(menu.idIcon)
                        onClick = {

                          this@FragmentMenu.onClick(menu)
                        }
                    })
                }
                TypeButton.CALL -> {
                    binding.body.addView(ButtonGreenLigthBecas.instance(requireContext(), menu).apply {
                        text = menu.text
                        setImageResource(menu.idIcon)
                        isEnabled = true
                        onClick = {
                            analyticsViewModel.sendClicks(menu.id,menu.otherInformation)

                            GlobalSettings.saveInterceptorPhone(false,GlobalSettings.getNumberPhone())
                            isEnabled = false

                            Thread.sleep(1000)

                            startActivity( Intent(Intent.ACTION_CALL).setData(Uri.parse("tel:" + menu.numberPhone)));
                            activity.finishAffinity()
                            exitProcess(0)

                        }
                    })

                }
                TypeButton.SN -> {
                    binding.body.addView(ButtonNSBecas.instance(requireContext(), menu).apply {
                        text = menu.text
                        setImageResource(menu.idIcon)
                        onClick = {

                           when(it){
                               ButtonNSBecas.NetworkSocial.youtube -> {
                                   analyticsViewModel.sendClicks(menu.id,menu.otherInformation+"YOUTUBE")

                                   ActivityUtils.openWebView(context = requireContext(),"https://www.youtube.com/becasbenitojuarezoficial")
                               }
                               ButtonNSBecas.NetworkSocial.facebook -> {
                                   analyticsViewModel.sendClicks(menu.id,menu.otherInformation+"FACEBOOK")

                                   ActivityUtils.openWebView(context = requireContext(),"https://www.facebook.com/BecasBenito/")
                               }
                               ButtonNSBecas.NetworkSocial.twitter ->{
                                   analyticsViewModel.sendClicks(menu.id,menu.otherInformation+"TWITTER")

                                   ActivityUtils.openWebView(context = requireContext(),"https://twitter.com/BecasBenito")
                               }
                           }

                        }
                        onClickVisibility = {
                            binding.rootScroll.post {
                                binding.rootScroll.smoothScrollTo(0, binding.rootScroll.bottom+it.height)

                            }
                        }
                    })

                }
            }
        }
        if (showButtonBack){
            binding.backButton.visibility = View.VISIBLE
            binding.footer.visibility =View.GONE
        }
        else
        {
            binding.backButton.visibility = View.GONE
            binding.footer.visibility =View.VISIBLE
            Picasso.get().load("https://gs.s10plus.com/images/logo_banner.jpeg").placeholder(R.drawable.sep).into(binding.footer)

            binding.footer.setOnClickListener {
                ActivityUtils.openWebView(requireContext(),"https://www.gob.mx/sep")

            }
        }


        if(headerText.isNotEmpty()){
            binding.header.text.text = headerText
            binding.header.image.visibility = View.GONE
        }
        else{
            binding.header.text_layout.visibility = View.GONE

        }



        binding.backButton.onClick={
            activity.onBackPressed()
        }

    }



    fun onClick(menu:MenuButtonsModel){
        when (menu.sendToFragment) {
            TypeView.MENU -> {
                activity.assignFragmentBackStack(
                    fragment = FragmentMenu.newInstance(
                        menu.subMenu ?: arrayOf(),
                        true,
                        menu.text
                    )
                )
            }
            TypeView.DETAILS -> {
                activity.assignFragmentBackStack(
                    fragment =
                    FragmentDetailMenu.newInstance(
                        menu.detailsModel ?: DetailsModel(), true, menu.text
                    )
                )
            }
            TypeView.LINK -> {
                ActivityUtils.openWebView(requireContext(),menu.link)
            }
            TypeView.WEBVIEW -> {
                activity.assignFragmentBackStack(
                    fragment =
                    FragmentWebView.newInstance(menu.link)
                )
            }
        }

        analyticsViewModel.sendClicks(menu.id,menu.otherInformation)


    }
    override fun setupObserver() {
    }

    override fun setupViewModel() {
        analyticsViewModel =BaseViewModel.getViewModel(this,AnalyticsViewModel::class.java)
    }

    override fun init() {
    }

    companion object {
        const val DATA ="DATA"
        const val BACK_BUTTON ="BACK_BUTTON"
        const val HEADER_TEXT ="HEADER_TEXT"

        fun newInstance(menuButtonsModel: Array<MenuButtonsModel>,showButtonBack:Boolean =false,headerText:String=""): FragmentMenu {
            val fragment = FragmentMenu()
            val args = Bundle()
            args.putParcelableArray(DATA, menuButtonsModel)
            args.putBoolean(BACK_BUTTON, showButtonBack)
            args.putString(HEADER_TEXT, headerText)

            //LogUtils.d(GsonUtils.toJson(menuButtonsModel))
            fragment.arguments = args
            return fragment
        }

        fun newInstanceMainMenu(context:Context): FragmentMenu {

            MenusCreator.idCont =0;
            return newInstance(arrayOf((
                    Menu_1(context = context)),
                    menu_2(context = context),
                    menu_3(context =context),
                menu_4(context =context),
                menu_5(context),
                MenuButtonsModel("Oficina Cerca de ti",R.drawable.ic_location_on,TypeView.LINK,link = "https://www.google.com/maps/search/Coordinación nacional de becas cerca de mí"),
                MenuButtonsModel("Chat en Línea",R.drawable.ic__579079462900,TypeView.WEBVIEW,link ="https://cariai.com/cVhlaTdqekZaZkkyL1VJUDd0VjFiUWRwb2tWbjdsQi9LWC9za2oyQllVLzNPWmRN?start_stamp=1588883184851&botId=547&appType=1&chatId=705765892&key=cVhlaTdqekZaZkkyL1VJUDd0VjFiUWRwb2tWbjdsQi9LWC9za2oyQllVLzNPWmRN&log_session=62547124&r=1&reg=3&Ancho=375&Alto=667&phoneNumber=" ),
                MenuButtonsModel("Atención de un agente",R.drawable.ic_phone,TypeView.CONTINUE_CALL,numberPhone = GlobalSettings.getNumberPhone(),typeButton = TypeButton.CALL ),
                MenuButtonsModel("Redes Sociales",R.drawable.ic_earth,TypeView.REDES_SOCIALES,numberPhone = GlobalSettings.getNumberPhone(),typeButton = TypeButton.SN ),

                ),false,"")
        }
    }
}