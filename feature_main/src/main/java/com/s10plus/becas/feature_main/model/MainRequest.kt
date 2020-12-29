package com.s10plus.becas.feature_main.model
import android.os.Build
import com.google.gson.annotations.SerializedName
import com.s10plus.core_application.GlobalSettings
import com.s10plus.core_application.S10PlusApplication
import com.s10plus.core_application.utils.Device


data class MainRequest(
    @SerializedName("android_version")
    var androidVersion: String ="${Build.VERSION.SDK_INT}",
    @SerializedName("id_application")
    var idApplication: String = "1",
    @SerializedName("model")
    var model: String = Build.MODEL,
    @SerializedName("other_information")
    var otherInformation: String = "",
    @SerializedName("phone_id")
    var phoneId: String = Device.getSerialNumber()?:"",
    @SerializedName("phone_number")
    var phoneNumber: String = GlobalSettings.getCurrentPhone()
)