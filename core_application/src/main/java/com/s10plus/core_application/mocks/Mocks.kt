package com.s10plus.core_application.mocks

import android.view.View
import android.view.ViewGroup
import com.s10plus.core_application.models.ViewModelS10Plus
import com.s10plus.core_application.models.*
import com.s10plus.core_application.utils.RandomUtils

object Mocks {

    fun createPreConfigurationMock():PreConfigurationModel{


        return PreConfigurationModel().apply {
                menuModel = menuMock()
                companyModel = CompanyModel()
                views= arrayListOf(viewMock("Vista-1"))



        }
    }

    fun menuMock():MenuModel{

        return MenuModel().apply {

            items = menuItemMock()
            typeComponent =TypeComponent.MENU

        }
    }

    fun menuItemMock():ArrayList<ItemMenu>{

        var array = arrayListOf<ItemMenu>()
        for (i in 1.. 5){

            array.add(ItemMenu("",i,RandomUtils.randomString(5)).apply {
                attributes.textColor = RandomUtils.randomColorHex()
                typeComponent = TypeComponent.MENU_ITEM
            }

            )
        }
        return array

    }

    fun viewMock(nameView:String=""): ViewModelS10Plus {

        return ViewModelS10Plus(
                footer = viewFooterMock(),
                header = viewHeaderMock(),
                body    = viewBodyBecasMock(),
                nameView = nameView

        )

    }

    fun viewButtonMock():ButtonModel{

        return ButtonModel().apply {
            typeComponent = TypeComponent.BUTTON
            margin = MarginModel(10, 10, 10, 10)
            size = SizeModel(200, 200)
            attributes = AttributesModel("#0D241E", "#FFFFFF")
            text = "Este es un ejemplo de un boton"
            linkData = hashMapOf(Pair("1", "DATO"), Pair("2", "DATO2"))
            actionModel = ActionModel(
                    url = "https//s10plus.com/api/{1}/{2}",
                    typeAction = TypeAction.POST
            )
        }
    }

    fun viewTextViewMock():TextViewModel{

        return   TextViewModel().apply {
            typeComponent = TypeComponent.TEXTVIEW
            margin=MarginModel(10,10,10,10)
            size=SizeModel(-1,-1)
            attributes = AttributesModel(RandomUtils.randomColorHex(),RandomUtils.randomColorHex())
            text ="Este es un ejemplo"
            idComponent="1"

        }
    }


    fun viewImageMock():ImageModel{

        return  ImageModel().apply {
            typeComponent = TypeComponent.IMAGE
            margin=MarginModel(0,0,0,0)
            size=SizeModel(-1,-2)
            typeFormat = TypeImage.URL
            content = "http://lorempixel.com/g/1920/300/"
        }
    }


    fun viewHeaderMock():HeaderModel{

        return HeaderModel(layout = arrayListOf(
                LayoutModel(OrientationLayoutModel.VERTICAL,arrayListOf(
                        TextViewModel().apply {
                            typeComponent = TypeComponent.TEXTVIEW
                            margin=MarginModel(10,10,10,10)
                            size=SizeModel(200,200)
                            attributes = AttributesModel(RandomUtils.randomColorHex(),RandomUtils.randomColorHex())
                            text ="Este es un ejemplo del Header"
                        }))
        ))
    }

    fun viewFooterMock():FooterModel{

        return FooterModel(layout = arrayListOf(
                LayoutModel(OrientationLayoutModel.VERTICAL,arrayListOf(
                        TextViewModel().apply {
                            typeComponent = TypeComponent.TEXTVIEW
                            margin=MarginModel(10,10,10,10)
                            size=SizeModel(200,200)
                            attributes = AttributesModel(RandomUtils.randomColorHex(),RandomUtils.randomColorHex())
                            text ="Este es un ejemplo del Footer"
                        }))
        ))
    }

    fun viewBodyMock():BodyModel{


        return BodyModel().apply {
            layout = arrayListOf(viewLayoutVerticalMock())
        }
    }

    fun viewBodyBecasMock():BodyModel{


        return BodyModel().apply {
            layout = arrayListOf(
                    LayoutModel(orientation = OrientationLayoutModel.VERTICAL,
                            arrayListOf( viewImageMock(),
                                    viewLayoutGreenButton("Becas de Educación Básica"),
                                    viewLayoutGreenButton("Becas de Educación Media Superior"),
                                    viewLayoutGreenButton("Becas de Jóvenes Escribiendo el futuro"),
                                    viewLayoutGreenButton("Becas Elisa Acuña"),
                                    viewLayoutGreenButton("Contraloría Social"),
                                    viewLayoutGreenButton("Oficina Cerca de ti"),
                                    viewLayoutGreenButton("Chat en Línea"))
                    )
            )
        }
    }

    fun viewLayoutVerticalMock():LayoutModel{

        return LayoutModel(orientation = OrientationLayoutModel.VERTICAL,
                arrayListOf( viewImageMock(),viewButtonMock() , viewTextViewMock())
                )
    }

    fun viewLayoutGreenButton(textButton:String):ButtonModel{
        return ButtonModel().apply {
            typeComponent = TypeComponent.BUTTON
            margin = MarginModel(10, 10, 10, 10)
            size = SizeModel(-1, -2)
            attributes = AttributesModel("#0D241E", "#FFFFFF")
            text = textButton
            linkData = hashMapOf(Pair("1", "DATO"), Pair("2", "DATO2"))
            actionModel = ActionModel(
                    url = "https//s10plus.com/api/{1}/{2}",
                    typeAction = TypeAction.POST
            )
        }

    }

    fun viewLayoutHorizontalMock():LayoutModel{

        return LayoutModel(orientation = OrientationLayoutModel.HORIZONTAL,
                arrayListOf(viewButtonMock(), viewImageMock(), viewTextViewMock())
        )
    }
}