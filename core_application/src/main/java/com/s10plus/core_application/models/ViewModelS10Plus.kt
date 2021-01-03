package com.s10plus.core_application.models

import com.google.gson.annotations.SerializedName
import com.s10plus.core_application.models.*

data class ViewModelS10Plus(
        @SerializedName("body")
        var body: BodyModel = BodyModel(),
        @SerializedName("footer")
        var footer: FooterModel = FooterModel(),
        @SerializedName("header")
        var header: HeaderModel = HeaderModel(),
        @SerializedName("nameView")
        var nameView: String = ""
)

