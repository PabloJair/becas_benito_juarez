package com.s10plus.becas.benitojuarez.splash

import android.app.Activity
import android.graphics.Rect
import android.view.View
import androidx.appcompat.app.AlertDialog

object DialogChooseState {

    private var state:HashMap<String, String> = hashMapOf(
        Pair("AGUASCALIENTES", "AGS."),
        Pair("BAJA CALIFORNIA", "BC."),
        Pair("BAJA CALIFORNIA SUR", "BCS."),
        Pair("CAMPECHE", "CAMP."),
        Pair("COAHUILA", "COAH."),
        Pair("COLIMA", "COL."),
        Pair("CHIAPAS", "CHIS."),
        Pair("CHIHUAHUA", "CHIH."),
        Pair("CIUDAD DE MEXICO", "CDMX."),
        Pair("DURANGO", "DGO."),
        Pair("GUANAJUATO", "GTO."),
        Pair("GUERRERO", "GRO."),
        Pair("HIDALGO", "HGO."),
        Pair("JALISCO", "JAL."),
        Pair("MEXICO", "EDO-MÉX."),
        Pair("MICHOACAN", "MICH."),
        Pair("MORELOS", "MOR."),
        Pair("NAYARIT", "NAY."),
        Pair("NUEVO LEON", "NL."),
        Pair("OAXACA", "OAX."),
        Pair("PUEBLA", "PUE."),
        Pair("QUERETARO", "QRO."),
        Pair("QUINTANA ROO", "QROO."),
        Pair("SAN LUIS POTOSI", "SLP."),
        Pair("SINALOA", "SIN."),
        Pair("SONORA", "SON."),
        Pair("TABASCO", "TAB."),
        Pair("TAMAULIPAS", "TAMPS."),
        Pair("TLAXCALA", "TLAX."),
        Pair("VERACRUZ", "VER."),
        Pair("YUCATAN", "YUC."),
        Pair("ZACATECAS", "ZAC."),

        )

    fun dialogState(
        context: Activity,
        onSuccess: ((Pair<String, String>) -> Unit),
        onCancel: () -> Unit
    ){
        val builder = AlertDialog.Builder(context)

        builder.setCustomTitle(View.inflate(context,R.layout.custom_title_state,null))
        builder.setCancelable(false)

        val animals = state.map { it.key }.toTypedArray()
        builder.setItems(animals) { dialog, which ->


            val key: String = state.keys.toTypedArray()[which]
            val value: String = state[key]!!

            onSuccess.invoke(Pair(key, value))

        }

// create and show the alert dialog
        val dialog = builder.create()
        dialog.setOnCancelListener {
            onCancel.invoke()
        }

        dialog.show()
        val displayRectangle = Rect()

        context.window.decorView.getWindowVisibleDisplayFrame(displayRectangle)

        dialog.window?.setLayout((displayRectangle.width() * 0.9f).toInt(),
            (displayRectangle.height() * 0.6f).toInt());



    }
}