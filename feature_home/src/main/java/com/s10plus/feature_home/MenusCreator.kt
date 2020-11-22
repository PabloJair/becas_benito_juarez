package com.s10plus.feature_home

import android.content.Context
import com.s10plus.feature_home.models.*

object MenusCreator {

    public fun Menu_1(context: Context):MenuButtonsModel{

        var text = arrayOf<TextDetailModel>(
        TextDetailModel(context.getString(R.string.text_1_menu_1),""),
        TextDetailModel(context.getString(R.string.text_2_menu_1),"https://www.gob.mx/becasbenitojuarez/articulos/beca-bienestar-para-las-familias-de-educacion-basica")
        )
       return MenuButtonsModel("Becas de Educación Básica",R.drawable.ic__595833269522,
            TypeView.DETAILS,detailsModel = DetailsModel(*text)
        )

    }


    public fun menu_2(context: Context):MenuButtonsModel{

        var text = arrayOf<TextDetailModel>(
            TextDetailModel(context.getString(R.string.text_1_menu_2),""),
            TextDetailModel(context.getString(R.string.text_2_menu_2),"https://www.gob.mx/becasbenitojuarez/articulos/beca-benito-juarez-para-jovenes-de-educacion-media-superior-216589")
        )
        var submenu =arrayOf(MenuButtonsModel(
            "Información General",
            0,TypeView.DETAILS,
            detailsModel = DetailsModel(*text),
            typeButton = TypeButton.BLACK),
            MenuButtonsModel(
                "Bienestar Azteca",
                0,TypeView.DETAILS,
                detailsModel = DetailsModel(*text),
                typeButton = TypeButton.BLACK)
            )
        return MenuButtonsModel("Becas de Educación Media Superios",R.drawable.ic__595833450197,TypeView.MENU,submenu,detailsModel = null,TypeButton.GREEN
        )

    }
}