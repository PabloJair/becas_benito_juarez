package com.s10plus.becas.benitojuarez.feature_login.models
import com.google.gson.annotations.SerializedName


data class LoginRequest(
    @SerializedName("android_version")
    var androidVersion: String = "",
    @SerializedName("id_application")
    var idApplication: String = "",
    @SerializedName("model")
    var model: String = "",
    @SerializedName("other_information")
    var otherInformation: String = "",
    @SerializedName("phone_id")
    var phoneId: String = "",
    @SerializedName("phone_number")
    var phoneNumber: String = ""
)