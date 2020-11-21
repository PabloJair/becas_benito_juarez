package com.s10plus.core_application.ui

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View

import android.widget.LinearLayout
import androidx.core.content.ContextCompat

import com.s10plus.core_application.R
import com.s10plus.core_application.databinding.ButtonBlackBinding
import com.s10plus.core_application.databinding.ButtonGreenBinding


class ButtonBlackBecas(context: Context, var attrs: AttributeSet?): LinearLayout(context, attrs){

    val binding:ButtonBlackBinding  = ButtonBlackBinding.inflate(LayoutInflater.from(context),this,true)

    var text = binding.text.text!!
    set(value) {
        binding.text.text = value
        field = value
    }
    var showImage =true
    set(value) {
        field = value

        binding.image.visibility = if(field) View.VISIBLE else View.GONE
    }

    var onClick:((view: View)->Unit)?=null

    init {
        try {

            orientation = LinearLayout.VERTICAL

            val ta = context.obtainStyledAttributes(attrs, R.styleable.green_button_attributes)
                .apply {
                    val referenceImg = getResourceId(R.styleable.green_button_attributes_image_button,R.drawable.ic_menu_gallery)
                    text =getText(R.styleable.green_button_attributes_text_button)?:""
                    binding.image.setImageResource(referenceImg)
                    binding.rootView.background=ContextCompat.getDrawable(context,R.drawable.drawable_button)
                    binding.text.setTextColor(Color.WHITE)
                    binding.rootView.setOnClickListener {

                        onClick?.invoke(it)
                    }
                    recycle()

                }

        }catch (e:Exception){}
    }



}