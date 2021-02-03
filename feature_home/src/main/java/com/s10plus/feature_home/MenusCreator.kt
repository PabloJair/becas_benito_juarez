package com.s10plus.feature_home

import android.content.Context
import com.s10plus.feature_home.models.*

object MenusCreator {

    const val click_url="CLICK/URL"
    const val click_detail="CLICK/DETALLE"
    const val click_continue_call="CLICK/CONTINUAR LLAMADA"
    const val click_menu="CLICK/MENU"
    const val click_REDES_SOCIALES="CLICK/REDES_SOCIALES"

    const val click_email="CLICK/EMAIL"
    const val click_sub_menu="CLICK/SUB_MENU"
    var idCont =0
    fun Menu_1(context: Context):MenuButtonsModel{

        var text = arrayOf<TextDetailModel>(
        TextDetailModel(
            context.getString(R.string.text_1_becas_edu_basica),
            "",
                id = 37,
                activity = click_detail,
                label =context.getString(R.string.text_1_becas_edu_basica),
                concept =context.getString(R.string.text_1_becas_edu_basica),
                parent_id = "1"
                ),
        TextDetailModel(
            context.getString(R.string.link_1_becas_edu_basica),
            "https://www.gob.mx/becasbenitojuarez/articulos/beca-bienestar-para-las-familias-de-educacion-basica",
                id = 2,
                activity = click_url,
                label ="https://www.gob.mx/becasbenitojuarez/articulos/beca-bienestar-para-las-familias-de-educacion-basica",
                concept = "preguntas frecuentes",
                parent_id = "1"
                )
        )
       return MenuButtonsModel("Becas de Educación Básica",R.drawable.ic__595833269522,
           TypeView.DETAILS,detailsModel = DetailsModel(*text),
               id =1,
               activity = click_detail,
               label ="Becas de Educación Básica",
               concept = "Becas de Educación Básica" )
    }


    public fun menu_2(context: Context):MenuButtonsModel{


        val info_General = arrayOf<TextDetailModel>(
            TextDetailModel(
                    context.getString(R.string.text_1_becas_edu_basica),
                    "",
                    id = 38,
                    activity = click_detail,
                    label =context.getString(R.string.text_1_becas_edu_basica),
                    concept =context.getString(R.string.text_1_becas_edu_basica),
                    parent_id = "6"

            ),
            TextDetailModel(
                    context.getString(R.string.link_1_becas_edu_basica),
                    "https://bienestarazteca.com/",
                    id =63,
                    activity = click_url,
                    label ="preguntas frecuentes",
                    concept = "https://bienestarazteca.com/",
                    parent_id = "6")
        )


        val submenu =arrayOf(
                MenuButtonsModel(
            "Información General",
            0,TypeView.DETAILS,
            detailsModel = DetailsModel(*info_General),
            typeButton = TypeButton.BLACK,
                        id =4,
                        activity = click_sub_menu,
                        label ="Información General",
                        concept = "Información General",
                        parent_id = "3"

        ),

            MenuButtonsModel(
                "Bienestar Azteca",
                0,TypeView.MENU,
                subMenu = menu_2_subMenu_2(context),
                typeButton = TypeButton.BLACK,
                    id =5,
                    activity = click_sub_menu,
                    label ="Bienestar Azteca",
                    concept = "Bienestar Azteca",
                    parent_id = "3"
                    )
            )
        return MenuButtonsModel(
                "Becas de Educación Media Superior",
                R.drawable.ic__595833450197,
                TypeView.MENU,submenu,
                detailsModel = null,TypeButton.GREEN,
                id =3,
                activity = click_detail,
                label ="Becas de Educación Media Superior",
                concept = "Becas de Educación Media Superior",
                parent_id = "0"
        )

    }

    public fun menu_3(context: Context):MenuButtonsModel{

        var info_General = arrayOf(
            TextDetailModel(context.getString(R.string.text1_becas_escribiendo_fut_ig),"",
                    id = 39,
                    activity = click_detail,
                    label =context.getString(R.string.text1_becas_escribiendo_fut_ig),
                    concept =context.getString(R.string.text1_becas_escribiendo_fut_ig),
                    parent_id = "19"),

        )

        var convocatoria = arrayOf(
            TextDetailModel(context.getString(R.string.text1_becas_escribiendo_fut_convo),"",
                    id = 40,
                    activity = click_detail,
                    label =context.getString(R.string.text1_becas_escribiendo_fut_convo),
                    concept =context.getString(R.string.text1_becas_escribiendo_fut_convo),
                    parent_id = "64"),
        )
        var submenu =arrayOf(MenuButtonsModel(
            "Información General",
            0,TypeView.DETAILS,
            detailsModel = DetailsModel(*info_General),
            typeButton = TypeButton.BLACK,
                id =20,
                activity = click_sub_menu,
                label ="Información General",
                concept = "Información General",
                parent_id = "19"
                ),
            MenuButtonsModel(
                "Convocatoria",
                0,TypeView.DETAILS,
                detailsModel = DetailsModel(*convocatoria),
                typeButton = TypeButton.BLACK,
                    id =64,
                    activity = click_sub_menu,
                    label ="Convocatoria",
                    concept = "Convocatoria",
                    parent_id = "19")
        )
        return MenuButtonsModel(
                "Becas Jovenes Escribiendo el futuro",
                R.drawable.ic__595919069972,TypeView.MENU,
                submenu,detailsModel = null,TypeButton.GREEN,
                id =19,
                activity = click_detail,
                label ="Becas Jovenes Escribiendo el futuro",
                concept = "Becas Jovenes Escribiendo el futuro",
                parent_id = "0"
        )

    }


    public fun menu_4(context: Context):MenuButtonsModel{

        var info_General = arrayOf<TextDetailModel>(
            TextDetailModel(context.getString(R.string.text1_becas_elisa_ig),"",
                    id = 41,
                    activity = click_detail,
                    label =context.getString(R.string.text1_becas_elisa_ig),
                    concept =context.getString(R.string.text1_becas_elisa_ig),
                    parent_id = "21"
                    ),
        )


        var submenu =arrayOf(MenuButtonsModel(
            "Información General",
            0,TypeView.DETAILS,
            detailsModel = DetailsModel(*info_General),
            typeButton = TypeButton.BLACK,
                id =22,
                activity = click_sub_menu,
                label ="Información General",
                concept = "Información General",
                parent_id = "21"),
            MenuButtonsModel("Convocatoria",
                0,TypeView.LINK,
                typeButton = TypeButton.BLACK,
                link = "https://www.gob.mx/becasbenitojuarez/articulos/becas-elisa-acuna",
                    id =23,
                    activity = click_url,
                    label ="Convocatoria",
                    concept = "https://www.gob.mx/becasbenitojuarez/articulos/becas-elisa-acuna",
                    parent_id = "21"),
            MenuButtonsModel("Resultados",
                0,TypeView.LINK,
                link = "https://www.gob.mx/becasbenitojuarez/articulos/becas-elisa-acuna",
                typeButton = TypeButton.BLACK,
                    id =24,
                    activity = click_url,
                    label ="Convocatoria",
                    concept = "https://www.gob.mx/becasbenitojuarez/articulos/becas-elisa-acuna",
                    parent_id = "21"))

        return MenuButtonsModel("Becas Elisa Acuña",
                R.drawable.ic__595833542780,
                TypeView.MENU,submenu,
                detailsModel = null,TypeButton.GREEN,
                id =21,
                activity = click_menu,
                label ="Becas Elisa Acuña",
                concept = "Becas Elisa Acuña",
                parent_id = "0")

    }

    public fun menu_5(context: Context):MenuButtonsModel{

        var info_General = arrayOf<TextDetailModel>(
            TextDetailModel(context.getString(R.string.text1_cs_ig),"",
                    id = 42,
                    activity = click_detail,
                    label =context.getString(R.string.text1_cs_ig),
                    concept =context.getString(R.string.text1_cs_ig),
                    parent_id = "27"),
        )
        var registrate = arrayOf<TextDetailModel>(
            TextDetailModel(context.getString(R.string.text1_registro_repre),
                    id =62,
                    activity = click_detail,
                    label =context.getString(R.string.text1_registro_repre),
                    concept = context.getString(R.string.text1_registro_repre),
                    parent_id = "27"),
            TextDetailModel(context.getString(R.string.link1_registro_repre),
                    email = "contraloria.basica@becasbenitojuarez.gob.mx",
                    id =31,
                    activity = click_email,
                    label ="contraloria.basica@becasbenitojuarez.gob.mx",
                    concept = " Representantes de Becas de Educación Básica:",
                    parent_id = "27"
                    ),
            TextDetailModel(context.getString(R.string.link2_registro_repre),
                    email = "contraloria.ems@becasbenitojuarez.gob.mx",
                    id =32,
                    activity = click_email,
                    label ="contraloria.basica@becasbenitojuarez.gob.mx",
                    concept = "Educación Media Superior:",
                    parent_id = "27"),
            TextDetailModel(context.getString(R.string.link3_registro_repre),
                    email = "contraloria.superior@becasbenitojuarez.gob.mx",
                    id =33,
                    activity = click_email,
                    label ="contraloria.basica@becasbenitojuarez.gob.mx",
                    concept = "Representantes de Jóvenes Escribiendo el Futuro:",
                    parent_id = "27"),
            TextDetailModel(context.getString(R.string.link4_registro_repre),
                    email = "contraloria.elisaa@becasbenitojuarez.gob.mx",
                    id =34,
                    activity = click_email,
                    label ="contraloria.basica@becasbenitojuarez.gob.mx",
                    concept = "Representantes de Becas Elisa Acuña:",
                    parent_id = "27"),

            )
        var qd = arrayOf<TextDetailModel>(
            TextDetailModel(context.getString(R.string.text1_qd),
                    id = 43,
                    activity = click_detail,
                    label =context.getString(R.string.text1_qd),
                    concept =context.getString(R.string.text1_qd),
                    parent_id = "30"
                    ),
            TextDetailModel(context.getString(R.string.link1_qd),"https://cnprospera-my.sharepoint.com/personal/monserrat_berber_becasbenitojuarez_gob_mx/_layouts/15/onedrive.aspx?id=%2Fpersonal%2Fmonserrat%5Fberber%5Fbecasbenitojuarez%5Fgob%5Fmx%2FDocuments%2FQuejas%20y%20Denuncias%2FFormato%5FQuejas%5FDenuncias%5F2020%5F%5F%2Epdf&parent=%2Fpersonal%2Fmonserrat%5Fberber%5Fbecasbenitojuarez%5Fgob%5Fmx%2FDocuments%2FQuejas%20y%20Denuncias&originalPath=aHR0cHM6Ly9jbnByb3NwZXJhLW15LnNoYXJlcG9pbnQuY29tL3BlcnNvbmFsL21vbnNlcnJhdF9iZXJiZXJfYmVjYXNiZW5pdG9qdWFyZXpfZ29iX214L19sYXlvdXRzLzE1L2d1ZXN0YWNjZXNzLmFzcHg_ZG9jaWQ9MTVmMTFiN2M0Nzg0YTRlYzZhYWQyM2ZmOTM2MTA3MTYzJmF1dGhrZXk9QWFYOHlCY1Y3akxRSEMtbEF1dDBDVzgmcnRpbWU9bWFGNlFHLVQyRWc",
                    id = 44,
                    activity = click_url,
                    concept = "https://cnprospera-my.sharepoint.com/personal/monserrat_berber_becasbenitojuarez_gob_mx/_layouts/15/onedrive.aspx?id=%2Fpersonal%2Fmonserrat%5Fberber%5Fbecasbenitojuarez%5Fgob%5Fmx%2FDocuments%2FQuejas%20y%20Denuncias%2FFormato%5FQuejas%5FDenuncias%5F2020%5F%5F%2Epdf&parent=%2Fpersonal%2Fmonserrat%5Fberber%5Fbecasbenitojuarez%5Fgob%5Fmx%2FDocuments%2FQuejas%20y%20Denuncias&originalPath=aHR0cHM6Ly9jbnByb3NwZXJhLW15LnNoYXJlcG9pbnQuY29tL3BlcnNvbmFsL21vbnNlcnJhdF9iZXJiZXJfYmVjYXNiZW5pdG9qdWFyZXpfZ29iX214L19sYXlvdXRzLzE1L2d1ZXN0YWNjZXNzLmFzcHg_ZG9jaWQ9MTVmMTFiN2M0Nzg0YTRlYzZhYWQyM2ZmOTM2MTA3MTYzJmF1dGhrZXk9QWFYOHlCY1Y3akxRSEMtbEF1dDBDVzgmcnRpbWU9bWFGNlFHLVQyRWc",
                    label = "Descarga el",
                    parent_id = "30"
                    ),
            TextDetailModel(context.getString(R.string.text2_qd),
                    id = 45,
                    activity = click_detail,
                    label =context.getString(R.string.text2_qd),
                    concept =context.getString(R.string.text2_qd),
                    parent_id = "30"
                    ),
            TextDetailModel(context.getString(R.string.link2_qd),email = "atencion@becasbenitojuarez.gob.mx",

                    id = 46,
                    activity = click_url,
                    concept = " Envía el formato (escaneado o por fotografía) al correo electrónico",
                    label = "Descarga el",
                    parent_id = "30"),

            )


        var submenu =arrayOf(MenuButtonsModel(
            "Información General",
            0,TypeView.DETAILS,
            detailsModel = DetailsModel(*info_General),
            typeButton = TypeButton.BLACK,
                id =26,
                activity = click_sub_menu,
                label ="Información General",
                concept = "Información General",
                parent_id = "25"
        ),
            MenuButtonsModel("¡Regístrate como representante!",
                0,TypeView.DETAILS,
                detailsModel = DetailsModel(*registrate),

                typeButton = TypeButton.BLACK,
                    id =27,
                    activity = click_sub_menu,
                    label ="¡Regístrate como representante!",
                    concept = "¡Regístrate como representante!",
                    parent_id = "25"),
            MenuButtonsModel("Denuncia Ciudadana",
                0,TypeView.LINK,
                link = "https://sidec.funcionpublica.gob.mx/#!/",
                typeButton = TypeButton.BLACK,
                    id =28,
                    activity = click_url,
                    label ="Denuncia Ciudadana",
                    concept = "https://sidec.funcionpublica.gob.mx/#!/",
                    parent_id = "25"),
            MenuButtonsModel("Ciudadanos Alertadores",
                0,TypeView.LINK,

                link = "https://alertadores.funcionpublica.gob.mx/",
                typeButton = TypeButton.BLACK,
                    id =29,
                    activity = click_url,
                    label ="Ciudadanos Alertadores",
                    concept = "https://alertadores.funcionpublica.gob.mx/",
                    parent_id = "25"
                    ),
            MenuButtonsModel("Quejas y Denuncias",
                0,TypeView.DETAILS,
                detailsModel = DetailsModel(*qd),
                typeButton = TypeButton.BLACK,
                    id =30,
                    activity = click_sub_menu,
                    label ="Quejas y Denuncias",
                    concept = "Quejas y Denuncias",
                    parent_id = "25")
        )
        return MenuButtonsModel("Contraloría Social",
                R.drawable.ic__595919234976,
                TypeView.MENU,
                submenu,
                detailsModel = null,
                TypeButton.GREEN,
                id =25,
                activity = click_menu,
                label ="Contraloría Social",
                concept = "Contraloría Social",
                parent_id = "0"
        )

    }

    public fun menu_2_subMenu_2(context: Context):Array<MenuButtonsModel>{

        var queEsBinenestar = arrayOf<TextDetailModel>(
            TextDetailModel(context.getString(R.string.text_1_que_es_bien_azteca),"",

                    id = 47,
                    activity = click_detail,
                    label =context.getString(R.string.text_1_que_es_bien_azteca),
                    concept =context.getString(R.string.text_1_que_es_bien_azteca),
                    parent_id = "6"),
            TextDetailModel(context.getString(R.string.link_1_que_es_bien_azteca),
                    "https://bienestarazteca.com/",
                    id =15,
                    activity = click_url,
                    label ="https://bienestarazteca.com/",
                    concept = "para que puedas recibir tu beca de forma electrónica.",
                    parent_id = "6"
            )
        )

        var etapa1 = arrayOf<TextDetailModel>(
            TextDetailModel(context.getString(R.string.text_1_etapa_1_registro),"",
                    id = 48,
                    activity = click_detail,
                    label =context.getString(R.string.text_1_etapa_1_registro),
                    concept =context.getString(R.string.text_1_etapa_1_registro),
                    parent_id = "7"),
            TextDetailModel(context.getString(R.string.link_1_etapa_1_registro),
                    "https://bienestarazteca.com/",
                    id =16,
                    activity = click_url,
                    label ="https://bienestarazteca.com/",
                    concept = "Lee el aviso de Privacidad.",
                    parent_id = "7"
                    ),
                    TextDetailModel(context.getString(R.string.text_2_etapa_1_registro),"",

                            id = 57,
                            activity = click_detail,
                            label =context.getString(R.string.text_2_etapa_1_registro),
                            concept =context.getString(R.string.text_2_etapa_1_registro),
                            parent_id = "7"),

        )


        var etapa2 = arrayOf<TextDetailModel>(
            TextDetailModel(context.getString(R.string.text_1_etapa_2_recibe),"",
                    id = 50,
                    activity = click_detail,
                    label =context.getString(R.string.text_1_etapa_2_recibe),
                    concept =context.getString(R.string.text_1_etapa_2_recibe),
                    parent_id = "8"),
            TextDetailModel(context.getString(R.string.link_1_etapa_2_recibe),
                    "https://bienestarazteca.com/",
                    id =17,
                    activity = click_url,
                    label ="https://bienestarazteca.com/",
                    concept = "1. Ingresa nuevamente ",
                    parent_id = "8")
        )

        var ayuda = arrayOf<TextDetailModel>(
            TextDetailModel(context.getString(R.string.text_1_ayuda_no_me_),"",
                    id = 51,
                    activity = click_detail,
                    label =context.getString(R.string.text_1_ayuda_no_me_),
                    concept =context.getString(R.string.text_1_ayuda_no_me_),
                    parent_id = "9"),
        )

        var nomellega = arrayOf<TextDetailModel>(
            TextDetailModel(context.getString(R.string.text_1_ayuda_no_me_),"",
                    id = 52,
                    activity = click_detail,
                    label =context.getString(R.string.text_1_ayuda_no_me_),
                    concept =context.getString(R.string.text_1_ayuda_no_me_),
                    parent_id = "10"),
        )

        var olvidepassword = arrayOf<TextDetailModel>(
            TextDetailModel(context.getString(R.string.link_1_olvide_password),
                    "https://bienestarazteca.com/",

                    id =18,
                    activity = click_url,
                    label ="https://bienestarazteca.com/",
                    concept = "1) Ingresa a ",
                    parent_id = "11"),

            TextDetailModel(context.getString(R.string.text1_olvide_password),"",
                    id = 53,
                    activity = click_detail,
                    label =context.getString(R.string.text1_olvide_password),
                    concept =context.getString(R.string.text1_olvide_password),
                    parent_id = "11"),
        )

        var micolonia = arrayOf<TextDetailModel>(

            TextDetailModel(context.getString(R.string.text1_no_aparece_colonia),"",
                    id = 54,
                    activity = click_detail,
                    label =context.getString(R.string.text1_no_aparece_colonia),
                    concept =context.getString(R.string.text1_no_aparece_colonia),
                    parent_id = "12"),
        )


        var duranteRegistro = arrayOf<TextDetailModel>(

            TextDetailModel(context.getString(R.string.text1_durante_registro),"",
                    id = 55,
                    activity = click_detail,
                    label =context.getString(R.string.text1_durante_registro),
                    concept =context.getString(R.string.text1_durante_registro),
                    parent_id = "13"),
        )


        var vigencia = arrayOf<TextDetailModel>(
            TextDetailModel(context.getString(R.string.text1_cual_es_vigencia),
                    "",

                    id =56,
                    activity = click_detail,
                    label =context.getString(R.string.text1_durante_registro),
                    concept = context.getString(R.string.text1_durante_registro),
                    parent_id = "14"),
            )
        return arrayOf(
            MenuButtonsModel("¿Qué es Bienestar Azteca?",
                    0,TypeView.DETAILS,
                    detailsModel = DetailsModel(*queEsBinenestar),
                    typeButton = TypeButton.BLACK,
                    id =6,
                    activity = click_detail,
                    label ="¿Qué es Bienestar Azteca?",
                    concept = "¿Qué es Bienestar Azteca?",
                    parent_id = "5"
            ),
            MenuButtonsModel("Etapa 1. Registro",
                    0,TypeView.DETAILS,
                    detailsModel = DetailsModel(*etapa1),
                    typeButton = TypeButton.BLACK,
                    id =7,
                    activity = click_detail,
                    label ="Etapa 1. Registro",
                    concept = "Etapa 1. Registro",
                    parent_id = "5"),
            MenuButtonsModel("Etapa 2. Recibe tu beca.",
                    0,TypeView.DETAILS,
                    detailsModel = DetailsModel(*etapa2),
                    typeButton = TypeButton.BLACK,
                    id =8,
                    activity = click_detail,
                    label ="Etapa 2. Recibe tu beca.",
                    concept = "Etapa 2. Recibe tu beca.",
                    parent_id = "5"),
            MenuButtonsModel("¡Ayuda! No me puedo registrar.",
                    0,TypeView.DETAILS,
                    detailsModel = DetailsModel(*ayuda),
                    typeButton = TypeButton.BLACK,
                    id =9,
                    activity = click_detail,
                    label ="¡Ayuda! No me puedo registrar.",
                    concept = "¡Ayuda! No me puedo registrar.",
                    parent_id = "5"),
            MenuButtonsModel(
                    "No me llega el código de verificación ni al correo ni al teléfono",
                    0,
                TypeView.DETAILS,
                    detailsModel = DetailsModel(*nomellega),
                    typeButton = TypeButton.BLACK,
                    id =10,
                    activity = click_detail,
                    label ="No me llega el código de verificación ni al correo ni al teléfono",
                    concept = "No me llega el código de verificación ni al correo ni al teléfono",
                    parent_id = "5"),
                    MenuButtonsModel(
                            "Olvidé mi contraseña",
                            0,TypeView.DETAILS,
                            detailsModel = DetailsModel(*olvidepassword),
                            typeButton = TypeButton.BLACK,
                            id =11,
                            activity = click_detail,
                            label ="Olvidé mi contraseña",
                            concept = "Olvidé mi contraseña",
                            parent_id = "5"),
        MenuButtonsModel("Mi colonia y/o mi código postal no\n" +
                "aparece en el listado desplegable",
                0,TypeView.DETAILS,
                detailsModel = DetailsModel(*micolonia),
                typeButton = TypeButton.BLACK,
                id =12,
                activity = click_detail,
                label ="Mi colonia y/o mi código postal no " +
                        "aparece en el listado desplegable",
                concept = "Mi colonia y/o mi código postal no " +
                        "aparece en el listado desplegable",
                parent_id = "5"
        ),
        MenuButtonsModel("Durante el registro el sistema no me\n" +
                "pidió tomarme la fotografía",
                0,TypeView.DETAILS,
                detailsModel = DetailsModel(*duranteRegistro),
                typeButton = TypeButton.BLACK,
                id =13,
                activity = click_detail,
                label ="Durante el registro el sistema no me " +
                        "pidió tomarme la fotografía",
                concept ="Durante el registro el sistema no me " +
                        "pidió tomarme la fotografía",
                parent_id = "5"),

                MenuButtonsModel("¿Cuál es la vigencia del código QR y del\n" + "código de barras?",
                        0,TypeView.DETAILS,
                        detailsModel = DetailsModel(*vigencia),
                        typeButton = TypeButton.BLACK,
                        id =14,
                        activity = click_detail,
                        label ="¿Cuál es la vigencia del código QR y del" +"código de barras?",
                        concept ="¿Cuál es la vigencia del código QR y del" + "código de barras?",
                        parent_id = "5")

        )

    }

}