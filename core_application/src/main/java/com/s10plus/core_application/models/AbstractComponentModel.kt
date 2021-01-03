package com.s10plus.core_application.models

import android.graphics.Color
import android.view.View
import android.view.ViewGroup

abstract class AbstractComponentModel {

    public var margin:MarginModel= MarginModel()
    public var size:SizeModel= SizeModel()
    public var attributes:AttributesModel= AttributesModel()
    public var typeComponent:TypeComponent=TypeComponent.NONE
    public var text:String=""
    public var idComponent =""
    public var linkData:HashMap<String,String> = hashMapOf()
    public var actionModel: ActionModel=ActionModel()
    abstract fun onConfigView(view:View)

    open fun init(view: View){
        onConfigGeneral(view)
        onConfigView(view)
    }
    open fun onConfigGeneral(view:View){
        view.layoutParams = ViewGroup.MarginLayoutParams(size.width,size.heigth)
        val marginParams =view.layoutParams as ViewGroup.MarginLayoutParams
        marginParams.setMargins(margin.left,margin.top,margin.rigth,margin.bottom)


        if(attributes.color.isNotEmpty())
            view.setBackgroundColor(Color.parseColor(attributes.color))



    }


    companion object{


    }

}