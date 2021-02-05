package com.s10plus.core_application.navigation

import android.content.Context
import android.content.Intent

object AppNavigation {


    fun openMainView(context: Context): Intent =
        intentClearTop(internalIntent(context, "com.s10plus.becas.benitojuarez.feature_main.open"))
    fun openSplash(context: Context): Intent =
        intentClearTop(internalIntent(context, "com.s10plus.becas.benitojuarez.splash.open"))



    private fun intentClearTop(intent: Intent): Intent {

        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
        return intent
    }

    private fun internalIntent(context: Context, action: String) =
        Intent(action).setPackage(context.packageName)
}