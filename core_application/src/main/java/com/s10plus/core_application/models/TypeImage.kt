package com.s10plus.core_application.models

import com.google.gson.annotations.SerializedName

enum class TypeImage(){
    @SerializedName("url")
    URL,
    @SerializedName("base64")
    BASE64,
    @SerializedName("none")
    NONE
}
