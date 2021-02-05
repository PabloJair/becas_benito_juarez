package com.s10plus.core_application.ui

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*


 object UtilsDate{
    fun getCurrentHour(zonaHoraria: String?): String {
        val formato = "HH:mm:ss"
        return getFormatDate(formato, zonaHoraria)
    }

    fun getCurrentDate(zonaHoraria: String?): String {
        val formato = "yyyy-MM-dd"
        return getFormatDate(formato, zonaHoraria)
    }

    @SuppressLint("SimpleDateFormat")
    fun getFormatDate(formato: String?, zonaHoraria: String?): String {
        val calendar: Calendar = Calendar.getInstance()
        val date: Date = calendar.time
        val sdf: SimpleDateFormat = SimpleDateFormat(formato)
        sdf.timeZone = TimeZone.getTimeZone(zonaHoraria)
        return sdf.format(date)
    }
}