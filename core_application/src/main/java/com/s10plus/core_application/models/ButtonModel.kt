package com.s10plus.core_application.models

import android.graphics.Color
import android.view.View
import com.google.gson.annotations.SerializedName
import com.s10plus.core_application.ui.ButtonS10Plus
import com.s10plus.core_application.ui.TextViewS1Plus

class ButtonModel():AbstractComponentModel(){
  override fun onConfigView(view: View) {
   var view = view as ButtonS10Plus

   view.text = text

   if(attributes.textColor.isNotEmpty())
    view.setTextColor(Color.parseColor(attributes.textColor))

  }


 }