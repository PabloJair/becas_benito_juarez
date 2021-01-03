package com.s10plus.core_application.models

import com.google.gson.annotations.SerializedName

data class AttributesModel(
        @SerializedName("color")
        var color: String = "",
        @SerializedName("text-color")
        var textColor: String = ""
){


}