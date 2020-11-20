package com.s10plus.core_application.models

import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import com.s10plus.core_application.ui.TextViewS1Plus

class TextViewModel:AbstractComponentModel() {

    override fun onConfigView(view: View) {
        var view = view as TextViewS1Plus
        if(attributes.textColor.isNotEmpty())
            view.setTextColor(Color.parseColor(attributes.textColor))


    }


}