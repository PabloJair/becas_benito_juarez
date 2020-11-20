package com.s10plus.becas.feature_main.view_model

import com.s10plus.core_application.GlobalSettings
import com.s10plus.core_application.database.enties.User
import com.s10plus.core_application.models.BaseModel
import com.s10plus.core_application.models.UserInformation
import com.s10plus.core_application.network.EndPoints
import io.reactivex.Observable
import retrofit2.http.*

interface MainService {

    @GET("/public/api/user/{idUser}/{idCompany}")
    fun getInformationUser(@Path("idUser") idUser:Int,@Path("idCompany") idCompany:Int): Observable<BaseModel<UserInformation>>

    @PATCH(EndPoints.SET_TOKEN_FIREBASE)
    fun setTokenFirebase(@Path("idUser") idUser:Int = GlobalSettings.getUser().idUser,@Body token: HashMap<String,String>): Observable<BaseModel<UserInformation>>



}