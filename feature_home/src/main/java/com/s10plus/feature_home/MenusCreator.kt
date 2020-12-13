package com.s10plus.feature_home

import android.content.Context
import com.s10plus.feature_home.models.*

object MenusCreator {

    public fun Menu_1(context: Context):MenuButtonsModel{

        var text = arrayOf<TextDetailModel>(
        TextDetailModel(context.getString(R.string.text_1_becas_edu_basica),""),
        TextDetailModel(context.getString(R.string.link_1_becas_edu_basica),"https://www.gob.mx/becasbenitojuarez/articulos/beca-bienestar-para-las-familias-de-educacion-basica")
        )
       return MenuButtonsModel("Becas de Educación Básica",R.drawable.ic__595833269522,
            TypeView.DETAILS,detailsModel = DetailsModel(*text)
        )

    }


    public fun menu_2(context: Context):MenuButtonsModel{


        var info_General = arrayOf<TextDetailModel>(
            TextDetailModel(context.getString(R.string.text_1_becas_edu_basica),""),
            TextDetailModel(context.getString(R.string.link_1_becas_edu_basica),"https://bienestarazteca.com/")
        )


        var submenu =arrayOf(MenuButtonsModel(
            "Información General",
            0,TypeView.DETAILS,
            detailsModel = DetailsModel(*info_General),
            typeButton = TypeButton.BLACK),
            MenuButtonsModel(
                "Bienestar Azteca",
                0,TypeView.MENU,
                subMenu = menu_2_subMenu_2(context),
                typeButton = TypeButton.BLACK)
            )
        return MenuButtonsModel("Becas de Educación Media Superior",R.drawable.ic__595833450197,TypeView.MENU,submenu,detailsModel = null,TypeButton.GREEN
        )

    }

    public fun menu_3(context: Context):MenuButtonsModel{

        var info_General = arrayOf<TextDetailModel>(
            TextDetailModel(context.getString(R.string.text1_becas_escribiendo_fut_ig),""),
        )

        var convocatoria = arrayOf<TextDetailModel>(
            TextDetailModel(context.getString(R.string.text1_becas_escribiendo_fut_convo),""),
        )
        var submenu =arrayOf(MenuButtonsModel(
            "Información General",
            0,TypeView.DETAILS,
            detailsModel = DetailsModel(*info_General),
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

        var info_General = arrayOf<TextDetailModel>(
            TextDetailModel(context.getString(R.string.text1_becas_elisa_ig),""),
        )


        var submenu =arrayOf(MenuButtonsModel(
            "Información General",
            0,TypeView.DETAILS,
            detailsModel = DetailsModel(*info_General),
            typeButton = TypeButton.BLACK),
            MenuButtonsModel("Convocatoria",
                0,TypeView.LINK,
                typeButton = TypeButton.BLACK,
                link = "https://www.gob.mx/becasbenitojuarez/articulos/becas-elisa-acuna"


            ),
            MenuButtonsModel("Resultados",
                0,TypeView.LINK,
                link = "https://www.gob.mx/becasbenitojuarez/articulos/becas-elisa-acuna",
                typeButton = TypeButton.BLACK)
        )
        return MenuButtonsModel("Becas Elisa Acuña",R.drawable.ic__595833542780,TypeView.MENU,submenu,detailsModel = null,TypeButton.GREEN)

    }

    public fun menu_5(context: Context):MenuButtonsModel{

        var info_General = arrayOf<TextDetailModel>(
            TextDetailModel(context.getString(R.string.text1_cs_ig),""),
        )
        var registrate = arrayOf<TextDetailModel>(
            TextDetailModel(context.getString(R.string.text1_registro_repre)),
            TextDetailModel(context.getString(R.string.link1_registro_repre),email = "contraloria.basica@becasbenitojuarez.gob.mx"),
            TextDetailModel(context.getString(R.string.link2_registro_repre),email = "contraloria.ems@becasbenitojuarez.gob.mx"),
            TextDetailModel(context.getString(R.string.link3_registro_repre),email = "contraloria.superior@becasbenitojuarez.gob.mx"),
            TextDetailModel(context.getString(R.string.link4_registro_repre),email = "contraloria.elisaa@becasbenitojuarez.gob.mx"),

            )
        var qd = arrayOf<TextDetailModel>(
            TextDetailModel(context.getString(R.string.text1_qd)),
            TextDetailModel(context.getString(R.string.link1_qd),"https://cnprospera-my.sharepoint.com/personal/monserrat_berber_becasbenitojuarez_gob_mx/_layouts/15/onedrive.aspx?id=%2Fpersonal%2Fmonserrat%5Fberber%5Fbecasbenitojuarez%5Fgob%5Fmx%2FDocuments%2FQuejas%20y%20Denuncias%2FFormato%5FQuejas%5FDenuncias%5F2020%5F%5F%2Epdf&parent=%2Fpersonal%2Fmonserrat%5Fberber%5Fbecasbenitojuarez%5Fgob%5Fmx%2FDocuments%2FQuejas%20y%20Denuncias&originalPath=aHR0cHM6Ly9jbnByb3NwZXJhLW15LnNoYXJlcG9pbnQuY29tL3BlcnNvbmFsL21vbnNlcnJhdF9iZXJiZXJfYmVjYXNiZW5pdG9qdWFyZXpfZ29iX214L19sYXlvdXRzLzE1L2d1ZXN0YWNjZXNzLmFzcHg_ZG9jaWQ9MTVmMTFiN2M0Nzg0YTRlYzZhYWQyM2ZmOTM2MTA3MTYzJmF1dGhrZXk9QWFYOHlCY1Y3akxRSEMtbEF1dDBDVzgmcnRpbWU9bWFGNlFHLVQyRWc"),
            TextDetailModel(context.getString(R.string.text2_qd)),
            TextDetailModel(context.getString(R.string.link2_qd),email = "atencion@becasbenitojuarez.gob.mx"),

            )


        var submenu =arrayOf(MenuButtonsModel(
            "Información General",
            0,TypeView.DETAILS,
            detailsModel = DetailsModel(*info_General),
            typeButton = TypeButton.BLACK),
            MenuButtonsModel("¡Regístrate como representante!",
                0,TypeView.DETAILS,
                detailsModel = DetailsModel(*registrate),

                typeButton = TypeButton.BLACK),
            MenuButtonsModel("Denuncia Ciudadana",
                0,TypeView.LINK,
                link = "https://alertadores.funcionpublica.gob.mx/",
                typeButton = TypeButton.BLACK),
            MenuButtonsModel("Ciudadanos Alertadores",
                0,TypeView.LINK,

                link = "https://sidec.funcionpublica.gob.mx/#!/",
                typeButton = TypeButton.BLACK),
            MenuButtonsModel("Quejas y Denuncias",
                0,TypeView.DETAILS,
                detailsModel = DetailsModel(*qd),
                typeButton = TypeButton.BLACK)
        )
        return MenuButtonsModel("Controloría Social",R.drawable.ic__595919234976,TypeView.MENU,submenu,detailsModel = null,TypeButton.GREEN
        )

    }

    public fun menu_2_subMenu_2(context: Context):Array<MenuButtonsModel>{

        var queEsBinenestar = arrayOf<TextDetailModel>(
            TextDetailModel(context.getString(R.string.text_1_que_es_bien_azteca),""),
            TextDetailModel(context.getString(R.string.link_1_que_es_bien_azteca),"https://bienestarazteca.com/")
        )

        var etapa1 = arrayOf<TextDetailModel>(
            TextDetailModel(context.getString(R.string.text_1_etapa_1_registro),""),
            TextDetailModel(context.getString(R.string.link_1_etapa_1_registro),"https://bienestarazteca.com/"),
                    TextDetailModel(context.getString(R.string.text_2_etapa_1_registro),""),

        )


        var etapa2 = arrayOf<TextDetailModel>(
            TextDetailModel(context.getString(R.string.text_1_etapa_2_recibe),""),
            TextDetailModel(context.getString(R.string.link_1_etapa_2_recibe),"https://bienestarazteca.com/")
        )

        var ayuda = arrayOf<TextDetailModel>(
            TextDetailModel(context.getString(R.string.text_1_ayuda_no_me_),""),
        )

        var nomellega = arrayOf<TextDetailModel>(
            TextDetailModel(context.getString(R.string.text_1_ayuda_no_me_),""),
        )

        var olvidepassword = arrayOf<TextDetailModel>(
            TextDetailModel(context.getString(R.string.link_1_olvide_password),"https://bienestarazteca.com/"),

            TextDetailModel(context.getString(R.string.text1_olvide_password),""),
        )

        var micolonia = arrayOf<TextDetailModel>(

            TextDetailModel(context.getString(R.string.text1_no_aparece_colonia),""),
        )


        var duranteRegistro = arrayOf<TextDetailModel>(

            TextDetailModel(context.getString(R.string.text1_durante_registro),""),
        )


        var vigencia = arrayOf<TextDetailModel>(
            TextDetailModel(context.getString(R.string.text1_cual_es_vigencia),"https://bienestarazteca.com/"),
            )
        return arrayOf(
            MenuButtonsModel("¿Qué es Bienestar Azteca?",0,TypeView.DETAILS,detailsModel = DetailsModel(*queEsBinenestar), typeButton = TypeButton.BLACK),
            MenuButtonsModel("Etapa 1. Registro",0,TypeView.DETAILS,detailsModel = DetailsModel(*etapa1), typeButton = TypeButton.BLACK),
            MenuButtonsModel("Etapa 2. Recibe tu beca.",0,TypeView.DETAILS,detailsModel = DetailsModel(*etapa2), typeButton = TypeButton.BLACK),
            MenuButtonsModel("¡Ayuda! No me puedo registrar.",0,TypeView.DETAILS,detailsModel = DetailsModel(*ayuda), typeButton = TypeButton.BLACK),
            MenuButtonsModel("No me llega el código de verificación ni al correo ni al teléfono",0,
                TypeView.DETAILS,detailsModel = DetailsModel(*nomellega), typeButton = TypeButton.BLACK),
                    MenuButtonsModel("Olvidé mi contraseña",0,TypeView.DETAILS,detailsModel = DetailsModel(*olvidepassword), typeButton = TypeButton.BLACK),
        MenuButtonsModel("Mi colonia y/o mi código postal no\n" +
                "aparece en el listado desplegable",0,TypeView.DETAILS,detailsModel = DetailsModel(*micolonia), typeButton = TypeButton.BLACK),
        MenuButtonsModel("Durante el registro el sistema no me\n" +
                "pidió tomarme la fotografía",0,TypeView.DETAILS,detailsModel = DetailsModel(*duranteRegistro), typeButton = TypeButton.BLACK),

                MenuButtonsModel("¿Cuál es la vigencia del código QR y del\n" +
                        "código de barras?",0,TypeView.DETAILS,detailsModel = DetailsModel(*vigencia), typeButton = TypeButton.BLACK)

        )

    }
}