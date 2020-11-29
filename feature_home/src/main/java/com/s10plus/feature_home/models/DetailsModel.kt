package com.s10plus.feature_home.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import retrofit2.http.Header

@Parcelize
class DetailsModel(vararg var texts:TextDetailModel):Parcelable
@Parcelize

class TextDetailModel(var text:String, var url:String="",var email:String=""):Parcelable