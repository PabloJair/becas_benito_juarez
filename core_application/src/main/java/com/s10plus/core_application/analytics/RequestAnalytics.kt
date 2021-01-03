package com.s10plus.core_application.analytics
import com.google.gson.annotations.SerializedName


data class RequestAnalytics(
    @SerializedName("id_action")
    var idAction: String = "",
    @SerializedName("other_information")
    var otherInformation: String = ""
)