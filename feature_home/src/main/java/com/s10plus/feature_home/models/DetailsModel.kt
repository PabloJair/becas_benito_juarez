package com.s10plus.feature_home.models

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.s10plus.feature_home.MenusCreator
import kotlinx.android.parcel.Parcelize
import retrofit2.http.Header

@Parcelize
class DetailsModel(vararg var texts:TextDetailModel):Parcelable
@Parcelize

class TextDetailModel(    @Expose(serialize = false) var text:String,    @Expose(serialize = false) var url:String="", @Expose(serialize = false) var email:String="",
                      var id:Int=0,
                      var moreInformation:String= if(url.isNotEmpty()) "CLICK/URL" else if(email.isNotEmpty()) "CLICK/SEND-EMAIL: $email" else "CLICK/DETALLES",
                      var activity:String="",var label:String ="",var concept:String="",var parent_id:String="",var sender:Boolean = true


):Parcelable