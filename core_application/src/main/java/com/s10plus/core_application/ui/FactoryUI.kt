package com.s10plus.core_application.ui

import android.content.Context
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.ImageView
import com.s10plus.core_application.models.*
import com.s10plus.core_application.models.TypeComponent.*

class FactoryUI {
    companion object{

        fun createView(context: Context,component:AbstractComponentModel,view:ViewGroup){

            when(component.typeComponent){
                BUTTON -> {
                    ButtonS10Plus.createForModel(context,null,component as ButtonModel,view)
                }
                TEXTVIEW -> {
                    TextViewS1Plus.createForModel(context,null,component as TextViewModel,view)

                }
                LABEL -> TODO()
                CARRUSEL -> TODO()
                IMAGE -> {
                    ImageViewS10Plus.createForModel(context,null,component as ImageModel,view)

                }
                MENU -> TODO()
                MENU_ITEM -> TODO()
                NONE -> TODO()
            }




        }
    }
}