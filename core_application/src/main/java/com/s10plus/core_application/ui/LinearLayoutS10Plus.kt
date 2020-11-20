package com.s10plus.core_application.ui

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.LinearLayout
import com.s10plus.core_application.models.LayoutModel
import com.s10plus.core_application.models.OrientationLayoutModel
import com.s10plus.core_application.models.TextViewModel

@SuppressLint("ViewConstructor")
class LinearLayoutS10Plus(context: Context, attrs: AttributeSet? = null, private val model: LayoutModel? =  LayoutModel(OrientationLayoutModel.VERTICAL,
        arrayListOf()))
    : LinearLayout(context, attrs),ILivecycleComponentView {
    override fun configuration() {


        model?.init(this)


    }

    fun createSubViews(){

        model?.components?.forEach {
            FactoryUI.createView(context,it,this)

        }

    }

    companion object{
        fun createForModel(context: Context, attrs: AttributeSet?=null,
                           model: LayoutModel = LayoutModel(OrientationLayoutModel.VERTICAL,
                                   arrayListOf()), view: ViewGroup,canCreateSubViews:Boolean = true): LinearLayoutS10Plus {
            return  LinearLayoutS10Plus(context,attrs,model).apply {
                configuration()
                view.addView(this)
                if (canCreateSubViews)
                    createSubViews()
            }

        }
    }
}