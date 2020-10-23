package com.s10plus.core_application.ui.dialog

import android.os.Parcelable
import androidx.annotation.DrawableRes
import androidx.annotation.RawRes
import com.s10plus.core_application.R
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
enum class TypeDialog():Parcelable {
    LOADER          ,
    SUCCESS         ,
    ERROR          ,
    INFORMATION     ,
    WARMING         ,


}