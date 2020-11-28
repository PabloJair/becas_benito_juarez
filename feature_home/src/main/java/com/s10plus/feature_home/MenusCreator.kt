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
            TextDetailModel(context.getString(R.string.text_2_sbm_2),"https://www.gob.mx/becasbenitojuarez/articulos/beca-benito-juarez-para-jovenes-de-educacion-media-superior-216589")
        )
        var submenu =arrayOf(MenuButtonsModel(
            "Información General",
            0,TypeView.DETAILS,
            detailsModel = DetailsModel(*text),
            typeButton = TypeButton.BLACK),
            MenuButtonsModel(
                "Bienestar Azteca",
                0,TypeView.MENU,
                subMenu = menu_2_subMenu_2(context),
                typeButton = TypeButton.BLACK)
            )
        return MenuButtonsModel("Becas de Educación Media Superios",R.drawable.ic__595833450197,TypeView.MENU,submenu,detailsModel = null,TypeButton.GREEN
        )

    }

    public fun menu_3(context: Context):MenuButtonsModel{

        var text = arrayOf<TextDetailModel>(
            TextDetailModel(context.getString(R.string.text_1_menu_2),""),
            TextDetailModel(context.getString(R.string.text_2_sbm_2),"https://www.gob.mx/becasbenitojuarez/articulos/beca-benito-juarez-para-jovenes-de-educacion-media-superior-216589")
        )
        var submenu =arrayOf(MenuButtonsModel(
            "Información General",
            0,TypeView.DETAILS,
            detailsModel = DetailsModel(*text),
            typeButton = TypeButton.BLACK),
            MenuButtonsModel(
                "Convocatoria",
                0,TypeView.MENU,
                subMenu = menu_2_subMenu_2(context),
                typeButton = TypeButton.BLACK)
        )
        return MenuButtonsModel("Becas Jovenes Escribiendo el futuro",R.drawable.ic__595919069972,TypeView.MENU,submenu,detailsModel = null,TypeButton.GREEN
        )

    }


    public fun menu_4(context: Context):MenuButtonsModel{

        var text = arrayOf<TextDetailModel>(
            TextDetailModel(context.getString(R.string.text_1_menu_2),""),
            TextDetailModel(context.getString(R.string.text_2_sbm_2),"https://www.gob.mx/becasbenitojuarez/articulos/beca-benito-juarez-para-jovenes-de-educacion-media-superior-216589")
        )
        var submenu =arrayOf(MenuButtonsModel(
            "Información General",
            0,TypeView.DETAILS,
            detailsModel = DetailsModel(*text),
            typeButton = TypeButton.BLACK),
            MenuButtonsModel("Convocatoria",
                0,TypeView.MENU,
                subMenu = menu_2_subMenu_2(context),
                typeButton = TypeButton.BLACK),
            MenuButtonsModel("Resultados",
                0,TypeView.MENU,
                subMenu = menu_2_subMenu_2(context),
                typeButton = TypeButton.BLACK)
        )
        return MenuButtonsModel("Becas Elisa Acuña",R.drawable.ic__595833542780,TypeView.MENU,submenu,detailsModel = null,TypeButton.GREEN)

    }

    public fun menu_5(context: Context):MenuButtonsModel{

        var text = arrayOf<TextDetailModel>(
            TextDetailModel(context.getString(R.string.text_1_menu_2),""),
            TextDetailModel(context.getString(R.string.text_2_sbm_2),"https://www.gob.mx/becasbenitojuarez/articulos/beca-benito-juarez-para-jovenes-de-educacion-media-superior-216589")
        )
        var submenu =arrayOf(MenuButtonsModel(
            "Información General",
            0,TypeView.DETAILS,
            detailsModel = DetailsModel(*text),
            typeButton = TypeButton.BLACK),
            MenuButtonsModel("¡Regístrate como representante!",
                0,TypeView.MENU,
                subMenu = menu_2_subMenu_2(context),
                typeButton = TypeButton.BLACK),
            MenuButtonsModel("Denuncia Ciudadana",
                0,TypeView.MENU,
                subMenu = menu_2_subMenu_2(context),
                typeButton = TypeButton.BLACK),
            MenuButtonsModel("Ciudadanos Alertadores",
                0,TypeView.MENU,
                subMenu = menu_2_subMenu_2(context),
                typeButton = TypeButton.BLACK),
            MenuButtonsModel("Quejas y Denuncias",
                0,TypeView.MENU,
                subMenu = menu_2_subMenu_2(context),
                typeButton = TypeButton.BLACK)
        )
        return MenuButtonsModel("Controloría Social",R.drawable.ic__595919234976,TypeView.MENU,submenu,detailsModel = null,TypeButton.GREEN
        )

    }

    public fun menu_2_subMenu_2(context: Context):Array<MenuButtonsModel>{

        var text = arrayOf<TextDetailModel>(
            TextDetailModel(context.getString(R.string.text_1_menu_2),""),
            TextDetailModel(context.getString(R.string.text_2_sbm_2),"https://www.gob.mx/becasbenitojuarez/articulos/beca-benito-juarez-para-jovenes-de-educacion-media-superior-216589")
        )

        return arrayOf(
            MenuButtonsModel("¿Qué es Bienestar Azteca?",0,TypeView.DETAILS,detailsModel = null, typeButton = TypeButton.BLACK),
            MenuButtonsModel("Etapa 1. Registro",0,TypeView.DETAILS,detailsModel = null, typeButton = TypeButton.BLACK),
            MenuButtonsModel("Etapa 2. Recibe tu beca.",0,TypeView.DETAILS,detailsModel = null, typeButton = TypeButton.BLACK),
            MenuButtonsModel("¡Ayuda! No me puedo registrar.",0,TypeView.DETAILS,detailsModel = null, typeButton = TypeButton.BLACK),
            MenuButtonsModel("No me llega el código de verificación ni al correo ni al teléfono",0,
                TypeView.DETAILS,detailsModel = null, typeButton = TypeButton.BLACK),
            MenuButtonsModel("Olvidé mi contraseña",0,TypeView.DETAILS,detailsModel = null, typeButton = TypeButton.BLACK),
                    MenuButtonsModel("Olvidé mi contraseña",0,TypeView.DETAILS,detailsModel = null, typeButton = TypeButton.BLACK),
        MenuButtonsModel("Mi colonia y/o mi código postal no\n" +
                "aparece en el listado desplegable",0,TypeView.DETAILS,detailsModel = null, typeButton = TypeButton.BLACK),
        MenuButtonsModel("Durante el registro el sistema no me\n" +
                "pidió tomarme la fotografía",0,TypeView.DETAILS,detailsModel = null, typeButton = TypeButton.BLACK),

                MenuButtonsModel("¿Cuál es la vigencia del código QR y del\n" +
                        "código de barras?",0,TypeView.DETAILS,detailsModel = null, typeButton = TypeButton.BLACK)

        )

    }
}