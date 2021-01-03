package com.s10plus.core_application.models

import android.view.View
import android.view.ViewGroup
import com.google.gson.annotations.SerializedName
import com.s10plus.core_application.ui.ImageViewS10Plus
import com.s10plus.core_application.ui.TextViewS1Plus
import com.squareup.picasso.Picasso

class ImageModel: AbstractComponentModel() {
        @SerializedName("typeFormat")
        var typeFormat: TypeImage = TypeImage.NONE
        @SerializedName("content")
        var content: String = ""
     override fun onConfigView(view: View) {
         val view = view as ImageViewS10Plus
         when(typeFormat){
             TypeImage.URL -> Picasso.get().load(content).into(view)
             TypeImage.BASE64 -> {

             }
             TypeImage.NONE -> {}
         }
     }


 }