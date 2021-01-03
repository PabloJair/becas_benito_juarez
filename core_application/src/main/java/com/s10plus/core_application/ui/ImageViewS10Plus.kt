package com.s10plus.core_application.ui

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import com.s10plus.core_application.models.ImageModel
import com.s10plus.core_application.models.TypeImage
import com.s10plus.core_application.models.TypeImage.*

import com.squareup.picasso.Picasso

@SuppressLint("AppCompatCustomView")
class ImageViewS10Plus(context: Context, attrs: AttributeSet?=null,private val model: ImageModel= ImageModel()) :
        ImageView(context,attrs),ILivecycleComponentView {
    override fun configuration() {
        model.init(this)

        when(model.typeFormat){
            URL -> {
                if(model.content.isNotEmpty())
                Picasso.get().load(model.content).into(this)
            }
            BASE64 -> {

            }
            NONE -> TODO()
        }
    }

    companion object{

        fun createForModel(context: Context, attrs: AttributeSet?=null, imageModel: ImageModel= ImageModel(), view: ViewGroup): ImageViewS10Plus {
            return  ImageViewS10Plus(context,attrs,imageModel).apply {
                configuration()
                view.addView(this)
            }

        }

    }
}