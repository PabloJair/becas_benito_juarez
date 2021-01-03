package com.s10plus.becas.benitojuarez.feature_login.view_model

import com.s10plus.core_application.models.BaseModel
import com.s10plus.core_application.models.UserInformation
import com.s10plus.core_application.network.EndPoints
import com.s10plus.becas.benitojuarez.feature_login.models.LoginRequest
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {

    @POST(EndPoints.LOAD)
    fun login(@Body request: LoginRequest): Observable<BaseModel<UserInformation>>
}