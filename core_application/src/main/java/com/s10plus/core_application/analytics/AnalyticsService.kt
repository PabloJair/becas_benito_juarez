package com.s10plus.core_application.analytics

import com.s10plus.core_application.GlobalSettings
import com.s10plus.core_application.models.BaseModel
import com.s10plus.core_application.models.UserInformation
import com.s10plus.core_application.network.EndPoints
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface AnalyticsService {
    @POST(EndPoints.SEND_CLICKS)
    fun sendClick(@Header("token") token:String = GlobalSettings.getToken(),@Body RQ:RequestAnalytics): Observable<BaseModel<Any>>

}