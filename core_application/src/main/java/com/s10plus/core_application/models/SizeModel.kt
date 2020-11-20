package com.s10plus.core_application.models

import com.google.gson.annotations.SerializedName

data class SizeModel(  @SerializedName("width")
                       var width: Int = 0,
                       @SerializedName("heigth")
                       var heigth: Int = 0,
                       )