package com.s10plus.core_application.ui.dialog

import android.os.Parcelable
import androidx.annotation.DrawableRes
import androidx.annotation.RawRes
import com.s10plus.core_application.R
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DialogData (val title:String="",
                       var message:String="",
                       val button1DialogData: ButtonDialogData= ButtonDialogData(),
                       val button2DialogData: ButtonDialogData=ButtonDialogData(),
                       val showIcon:Boolean = false,
                       @RawRes var lottieFile:Int?= null,
                       @DrawableRes val icon:Int?=null):Parcelable

@Parcelize
data class ButtonDialogData (var text:String="",
                             @DrawableRes val btn2Icon:Int=R.drawable.ic_person,
                             var show:Boolean=true):Parcelable