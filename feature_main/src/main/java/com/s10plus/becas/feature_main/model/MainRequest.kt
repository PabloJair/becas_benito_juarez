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
    var otherInformation: OtherInformation = OtherInformation(),
    @SerializedName("phone_id")
    var phoneId: String = Device.getSerialNumber()?:"",
    @SerializedName("phone_number")
    var phoneNumber: String = if(GlobalSettings.getCurrentPhone()=="+5210000000000") "" else GlobalSettings.getCurrentPhone()
)
data class OtherInformation(
    @SerializedName("lat")
    var lat: Double = GlobalSettings.lat,
    @SerializedName("long")
    var long: Double =GlobalSettings.lng,
    @SerializedName("origin")
    var origin: String = "ANDROID-APP",
    @SerializedName("state")
    var state: String = GlobalSettings.getState()?.second?:""

)