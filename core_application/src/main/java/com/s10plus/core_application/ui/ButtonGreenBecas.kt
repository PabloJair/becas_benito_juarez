package com.s10plus.core_application.ui

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import com.google.android.material.button.MaterialButton
import com.s10plus.core_application.R
import com.s10plus.core_application.databinding.ButtonGreenBinding


class ButtonGreenBecas(context: Context, attrs: AttributeSet? = null): LinearLayout(context, attrs){

    val binding:ButtonGreenBinding by lazy { ButtonGreenBinding.inflate(LayoutInflater.from(context),this,false) }

    var text = binding.text.text
    init {

        val ta = context.obtainStyledAttributes(attrs, R.styleable.greenButtonAttributes, 0, 0)


        try {
            orientation = LinearLayout.VERTICAL
            text =ta.getText(R.styleable.greenButtonAttributes_text)?:""

            binding.rootView.background=ContextCompat.getDrawable(context,R.drawable.drawable_button)
            binding.text.setTextColor(Color.WHITE)
            addView(binding.root)


        } finally {
            invalidate()
            ta.recycle()
        }
    }
}