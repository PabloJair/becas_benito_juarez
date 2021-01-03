package com.s10plus.core_application.models

import android.view.View
import android.widget.LinearLayout
import com.google.gson.annotations.SerializedName
import com.s10plus.core_application.ui.LinearLayoutS10Plus
import com.s10plus.core_application.ui.TextViewS1Plus

class LayoutModel(
        var orientation:OrientationLayoutModel=OrientationLayoutModel.VERTICAL,
        var components:ArrayList<AbstractComponentModel>):AbstractComponentModel() {
    override fun onConfigView(view: View) {
        var view = view as LinearLayoutS10Plus

        view.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)

        view. orientation = if(orientation == OrientationLayoutModel.VERTICAL)  LinearLayout.VERTICAL else LinearLayout.HORIZONTAL

    }


}