package com.s10plus.core_application.models

import android.view.View

class MenuModel(var items:ArrayList<ItemMenu> = arrayListOf()):AbstractComponentModel() {
    override fun onConfigView(view: View) {
    }
}