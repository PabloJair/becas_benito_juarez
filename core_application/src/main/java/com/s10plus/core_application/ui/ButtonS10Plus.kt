package com.s10plus.core_application.ui

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.s10plus.core_application.models.AbstractComponentModel
import com.s10plus.core_application.models.ButtonModel
import com.s10plus.core_application.models.TextViewModel

@SuppressLint("AppCompatCustomView")
class ButtonS10Plus(context: Context, attrs: AttributeSet?=null, private val model: ButtonModel = ButtonModel())
    : Button(context,attrs),ILivecycleComponentView {
    override fun configuration() {


        model.init(this)
        text = model.text

        if(model.attributes.textColor.isNotEmpty()){
            setTextColor(Color.parseColor(model.attributes.textColor?:"#FFFFFFFF"))
        }
    }


    companion object{
        fun createForModel(context: Context, attrs: AttributeSet?=null, model: ButtonModel= ButtonModel(), view: ViewGroup): ButtonS10Plus {
            return  ButtonS10Plus(context,attrs,model).apply {
                configuration()
                view.addView(this)
            }

        }

    }
}