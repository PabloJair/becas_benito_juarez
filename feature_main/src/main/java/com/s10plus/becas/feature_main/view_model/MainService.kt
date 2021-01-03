package com.s10plus.becas.feature_main.view_model

import com.s10plus.becas.feature_main.model.MainRequest
import com.s10plus.core_application.models.BaseModel
import com.s10plus.core_application.network.EndPoints
import io.reactivex.Observable
import retrofit2.http.*

interface MainService {


    @POST(EndPoints.LOAD)
    fun load(@Body mainRequest: MainRequest=MainRequest()): Observable<BaseModel<Any>>



}