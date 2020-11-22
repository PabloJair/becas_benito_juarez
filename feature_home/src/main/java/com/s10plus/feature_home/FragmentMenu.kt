package com.s10plus.feature_home

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.core.view.marginBottom
import com.s10plus.core_application.base_ui.BaseFragment
import com.s10plus.core_application.ui.ButtonBlackBecas
import com.s10plus.core_application.ui.ButtonGreenBecas
import com.s10plus.feature_home.MenusCreator.Menu_1
import com.s10plus.feature_home.MenusCreator.menu_2
import com.s10plus.feature_home.databinding.FragmentHomeBinding
import com.s10plus.feature_home.models.DetailsModel
import com.s10plus.feature_home.models.MenuButtonsModel
import com.s10plus.feature_home.models.TypeButton
import com.s10plus.feature_home.models.TypeView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.header_layout.view.*
import kotlin.properties.Delegates

class FragmentMenu:BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

     lateinit var menus:Array<MenuButtonsModel>
     var showButtonBack by Delegates.notNull<Boolean>()
    lateinit var headerText: String

    override fun setupView() {
        menus = (requireArguments().getParcelableArray(DATA)?:throw Exception("Sin Menus ")) as Array<MenuButtonsModel>
        showButtonBack = requireArguments().getBoolean(BACK_BUTTON,false)
        headerText = requireArguments().getString(HEADER_TEXT,"")

        for (menu in menus){

            if(menu.typeButton == TypeButton.GREEN) {
                binding.body.addView(ButtonGreenBecas.instance(requireContext(), menu).apply {
                    text = menu.text
                    setImageResource(menu.idIcon)
                    onClick = {

                        if (menu.sendToFragment == TypeView.MENU) {
                            activity.assignFragmentBackStack(
                                fragment = FragmentMenu.newInstance(
                                    menu.subMenu ?: arrayOf(),
                                    true,
                                    menu.text
                                )
                            )
                        } else if (menu.sendToFragment == TypeView.DETAILS) {
                            activity.assignFragmentBackStack(
                                fragment =
                                FragmentDetailMenu.newInstance(
                                    menu.detailsModel ?: DetailsModel(), true, menu.text
                                )
                            )
                        }
                    }
                })
            }else if(menu.typeButton == TypeButton.BLACK) {
                binding.body.addView(ButtonBlackBecas.instance(requireContext(), menu).apply {
                    text = menu.text
                    setImageResource(menu.idIcon)
                    onClick = {

                        if (menu.sendToFragment == TypeView.MENU) {
                            activity.assignFragmentBackStack(
                                fragment = FragmentMenu.newInstance(
                                    menu.subMenu ?: arrayOf(),
                                    true,
                                    menu.text
                                )
                            )
                        } else if (menu.sendToFragment == TypeView.DETAILS) {
                            activity.assignFragmentBackStack(
                                fragment =
                                FragmentDetailMenu.newInstance(
                                    menu.detailsModel ?: DetailsModel(), true, menu.text
                                )
                            )
                        }
                    }
                })
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

        }


        if(headerText.isNotEmpty()){
            binding.header.text.text = headerText
            binding.header.image.visibility = View.GONE
        }
        else{
            binding.header.text_layout.visibility = View.GONE

        }




    }



    override fun setupObserver() {
    }

    override fun setupViewModel() {
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

            fragment.arguments = args
            return fragment
        }

        fun newInstanceMainMenu(context:Context): FragmentMenu {

            return newInstance(arrayOf((
                    Menu_1(context = context)),
                    menu_2(context = context),
                MenuButtonsModel("Becas Jovenes Escribiendo el futuro",R.drawable.ic__595919069972,TypeView.MENU),
                MenuButtonsModel("Becas Elisa Acuña",R.drawable.ic__595833542780,TypeView.MENU),
                MenuButtonsModel("Controloría Social",R.drawable.ic__595919234976,TypeView.MENU),
                MenuButtonsModel("Oficina Cerca de ti",R.drawable.ic_location_on,TypeView.MENU),
                MenuButtonsModel("Chat en Línea",R.drawable.ic__579079462900,TypeView.MENU),

                ),false,"")
        }
    }
}