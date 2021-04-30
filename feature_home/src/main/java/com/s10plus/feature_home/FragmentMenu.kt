package com.s10plus.feature_home

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import com.blankj.utilcode.util.GsonUtils
import com.blankj.utilcode.util.LogUtils
import com.s10plus.core_application.GlobalSettings
import com.s10plus.core_application.analytics.AnalyticsViewModel
import com.s10plus.core_application.base_ui.ActivityUtils
import com.s10plus.core_application.base_ui.BaseFragment
import com.s10plus.core_application.base_ui.BaseViewModel
import com.s10plus.core_application.ui.*
import com.s10plus.feature_home.MenusCreator.Menu_1
import com.s10plus.feature_home.MenusCreator.click_REDES_SOCIALES
import com.s10plus.feature_home.MenusCreator.click_continue_call
import com.s10plus.feature_home.MenusCreator.click_url
import com.s10plus.feature_home.MenusCreator.menu_2
import com.s10plus.feature_home.MenusCreator.menu_3
import com.s10plus.feature_home.MenusCreator.menu_4
import com.s10plus.feature_home.MenusCreator.menu_5
import com.s10plus.feature_home.databinding.FragmentHomeBinding
import com.s10plus.feature_home.models.*
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.header_layout.view.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.properties.Delegates
import kotlin.system.exitProcess

class FragmentMenu:BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

     lateinit var menus:Array<MenuButtonsModel>
     var showButtonBack by Delegates.notNull<Boolean>()
    lateinit var headerText: String

    lateinit var analyticsViewModel: AnalyticsViewModel
    override fun setupView() {
        menus = (requireArguments().getParcelableArray(DATA)?:throw Exception("Sin Menus ")) as Array<MenuButtonsModel>
        showButtonBack = requireArguments().getBoolean(BACK_BUTTON, false)
        headerText = requireArguments().getString(HEADER_TEXT, "")

        val format = SimpleDateFormat("HH", Locale.US)
        val hour: String = format.format(Date())


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
                    if(hour.toInt() in 9..18) {
                        binding.body.addView(ButtonGreenLigthBecas.instance(requireContext(), menu).apply {
                            text = menu.text
                            setImageResource(menu.idIcon)
                            isEnabled = true
                            onClick = {
                                analyticsViewModel.sendClicks(menu.id, menu.otherInformation)

                                GlobalSettings.saveInterceptorPhone(false, GlobalSettings.getNumberPhone())
                                isEnabled = false

                                Thread.sleep(1000)

                                startActivity(Intent(Intent.ACTION_DIAL).setData(Uri.parse("tel:" + menu.numberPhone)));
                                activity.finishAffinity()
                                exitProcess(0)

                            }
                        })

                    }
                }
                TypeButton.SN -> {
                    binding.body.addView(ButtonNSBecas.instance(requireContext(), menu).apply {
                        text = menu.text
                        setImageResource(menu.idIcon)
                        onClick = {

                            when (it) {
                                ButtonNSBecas.NetworkSocial.youtube -> {
                                    analyticsViewModel.sendClicks(65, "YOUTUBE")

                                    ActivityUtils.openWebView(context = requireContext(), "https://www.youtube.com/becasbenitojuarezoficial")
                                }
                                ButtonNSBecas.NetworkSocial.facebook -> {
                                    analyticsViewModel.sendClicks(64, "FACEBOOK")

                                    ActivityUtils.openWebView(context = requireContext(), "https://www.facebook.com/BecasBenito/")
                                }
                                ButtonNSBecas.NetworkSocial.twitter -> {
                                    analyticsViewModel.sendClicks(63,  "TWITTER")

                                    ActivityUtils.openWebView(context = requireContext(), "https://twitter.com/BecasBenito")
                                }
                            }

                        }
                        onClickVisibility = {
                            binding.rootScroll.post {
                                binding.rootScroll.smoothScrollTo(0, binding.rootScroll.bottom + it.height)

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
                ActivityUtils.openWebView(requireContext(), "https://www.gob.mx/sep")
                analyticsViewModel.sendClicks(66, "SEP")


            }
        }


        if(headerText.isNotEmpty()){
            binding.header.text.text = headerText
            binding.header.image.visibility = View.GONE
            binding.header.attentionHour.visibility = View.GONE

        }
        else{
            binding.header.text_layout.visibility = View.GONE
            binding.header.attentionHour.visibility = View.VISIBLE


        }
        if(hour.toInt() in 9..18) {
            binding.header.attentionHour.visibility = View.GONE
        }else {
            binding.header.attentionHour.visibility = View.VISIBLE

        }



        binding.backButton.onClick={
            activity.onBackPressed()
        }

    }



    fun onClick(menu: MenuButtonsModel){
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
                ActivityUtils.openWebView(requireContext(), menu.link)
            }
            TypeView.WEBVIEW -> {
                activity.assignFragmentBackStack(
                        fragment =
                        FragmentWebView.newInstance(menu.link)
                )
            }
        }

        analyticsViewModel.sendClicks(menu.id, menu.otherInformation)


    }
    override fun setupObserver() {
    }

    override fun setupViewModel() {
        analyticsViewModel =BaseViewModel.getViewModel(this, AnalyticsViewModel::class.java)
    }

    override fun init() {
    }

    companion object {
        const val DATA ="DATA"
        const val BACK_BUTTON ="BACK_BUTTON"
        const val HEADER_TEXT ="HEADER_TEXT"

        fun newInstance(menuButtonsModel: Array<MenuButtonsModel>, showButtonBack: Boolean = false, headerText: String = ""): FragmentMenu {
            val fragment = FragmentMenu()
            val args = Bundle()
            args.putParcelableArray(DATA, menuButtonsModel)
            args.putBoolean(BACK_BUTTON, showButtonBack)
            args.putString(HEADER_TEXT, headerText)

         // var table = AnalitycsUtils.menuButtonsModelToLog(menuButtonsModel);
         //  LogUtils.d(GsonUtils.toJson(menuButtonsModel))
            fragment.arguments = args
            return fragment
        }

        fun newInstanceMainMenu(context: Context): FragmentMenu {


            return newInstance(arrayOf(
                    (
                            Menu_1(context = context)),
                    menu_2(context = context),
                    menu_3(context = context),
                    menu_4(context = context),
                    menu_5(context),
                    MenuButtonsModel(
                            "Oficina Cerca de ti", R.drawable.ic_location_on,
                            TypeView.LINK, link = "https://www.google.com/maps/search/Coordinación nacional de becas cerca de mí",
                            id = 62,
                            activity = click_url,
                            label = "https://www.google.com/maps/search/Coordinación nacional de becas cerca de mí",
                            concept = "OFICINA CERCA DE TI",
                            parent_id = "0"
                    ),
                    MenuButtonsModel("Chat en Línea",
                            R.drawable.ic__579079462900,
                            TypeView.WEBVIEW,
                            link = "https://cariai.com/cVhlaTdqekZaZkkyL1VJUDd0VjFiUWRwb2tWbjdWQi9LWC9za2oyQllWbmlLOWhlQ0dneFlhSTFqTzNmb2lSY3liL2MveVVVT3JlMXVTTHpEQT09?phoneNumber=${GlobalSettings.getCurrentPhone(true)}",
                            id = 61,
                            activity = click_url,
                            label = "https://cariai.com/cVhlaTdqekZaZkkyL1VJUDd0VjFiUWRwb2tWbjdWQi9LWC9za2oyQllWbmlLOWhlQ0dneFlhSTFqTzNmb2lSY3liL2MveVVVT3JlMXVTTHpEQT09?phoneNumber=${GlobalSettings.getCurrentPhone(true)}",
                            concept = "CHAT EN LINEA",
                            parent_id = "0"
                    ),
                    MenuButtonsModel("Atención de un agente",
                            R.drawable.ic_phone,
                            TypeView.CONTINUE_CALL,
                            numberPhone = GlobalSettings.getNumberPhone(),
                            typeButton = TypeButton.CALL,
                            id = 60,
                            activity = click_continue_call,
                            label = GlobalSettings.getNumberPhone(),
                            concept = "CONTINUAR LA LLAMADA",
                            parent_id = "0"),
                    MenuButtonsModel("Redes Sociales", R.drawable.ic_earth, TypeView.REDES_SOCIALES, numberPhone = "", typeButton = TypeButton.SN,
                            id = 59,
                            activity = click_REDES_SOCIALES,
                            label = "Redes Sociales",
                            concept = "Redes Sociales",
                            parent_id = "0"
                    ),

                    ), false, "")
        }
    }
}