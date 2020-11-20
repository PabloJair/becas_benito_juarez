package com.s10plus.core_application.models

import com.google.gson.annotations.SerializedName


data class MarginModel(
        @SerializedName("bottom")
        var bottom: Int = 0,
        @SerializedName("left")
        var left: Int = 0,
        @SerializedName("rigth")
        var rigth: Int = 0,
        @SerializedName("top")
        var top: Int = 0
)
