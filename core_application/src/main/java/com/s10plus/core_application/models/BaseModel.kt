package com.s10plus.core_application.models
import com.google.gson.annotations.SerializedName


open class BaseModel<T>(
    @SerializedName("Code")
    var code: Int,
    @SerializedName("Data")
    var data: T,
    @SerializedName("Error")
    var error: String?="",
    @SerializedName("Message")
    var message: String?=""
)

