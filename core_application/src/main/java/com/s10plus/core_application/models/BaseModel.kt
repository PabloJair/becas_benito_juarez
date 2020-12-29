package com.s10plus.core_application.models
import com.google.gson.annotations.SerializedName


open class BaseModel<T>(
    @SerializedName("Code")
    var code: Int,
    @SerializedName("Data")
    var data: T,
    @SerializedName("Error")
    var error: String?="",
    @SerializedName("token")
    var token: String?="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2MDQ4NjM5NzgsImV4cCI6MTYwNDg2NzU3OCwiYXVkIjoiNzU1YjU3MDhkN2IxMDhkODE2YzViOTZlOGJkMDExMGU1MzAxMWRhOSIsImRhdGEiOnsicm9sIjoiMSIsImlkX3VzdWFyaW8iOiJ1c2VyMSIsInVzZXJOYW1lIjoiYWxmb25zb2xvcGV6IiwiZnVsbFVzZXJOYW1lIjoiQWxmb25zbyBMXHUwMGYzcGV6IiwiaWRJbnN0aXR1Y2lvbiI6bnVsbH19.ioU-ZSzdaVDX8FcQXLNuKmFesFnmDsxQDS0iSecD6_4",
    @SerializedName("Message")
    var message: String?="",
    @SerializedName("status")
    var status: Int=0
)

