package com.s10plus.core_application.ui

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.TextView
import com.s10plus.core_application.models.TextViewModel

@SuppressLint("AppCompatCustomView")
class TextViewS1Plus(context: Context, attrs: AttributeSet?=null, private val model: TextViewModel = TextViewModel())
    : TextView(context,attrs),ILivecycleComponentView {
    override fun configuration() {
        model.init(this)
        text = model.text
    }

    companion object{

        fun createForModel(context: Context, attrs: AttributeSet?=null, model: TextViewModel= TextViewModel(), view: ViewGroup): TextViewS1Plus {
            return  TextViewS1Plus(context,attrs,model).apply {
                configuration()
                view.addView(this)
            }

        }

    }


}