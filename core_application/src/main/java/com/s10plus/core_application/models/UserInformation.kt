package com.s10plus.core_application.models
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserInformation(
    @SerializedName("email")
    var email: String="",
    @SerializedName("icon_area")
    var iconArea: String="",
    @SerializedName("id_rol")
    var idRol: Int=-1,
    @SerializedName("id_user")
    var idUser: Int=-1,
    @SerializedName("logotype_company")
    var logotypeCompany: String="",
    @SerializedName("maternal_surname")
    var maternalSurname: String="",
    @SerializedName("modules")
    var modules: List<Module> = arrayListOf(),
    @SerializedName("name")
    var name: String="",
    @SerializedName("name_area")
    var nameArea: String="",
    @SerializedName("name_company")
    var nameCompany: String="",
    @SerializedName("id_company")
    var idCompany: Int=-1,
    @SerializedName("name_rol")
    var nameRol: String="",
    @SerializedName("name_status_user")
    var nameStatusUser: Int=0,
    @SerializedName("no_employee")
    var noEmployee: String="",
    @SerializedName("paternal_surname")
    var paternalSurname: String="",
    @SerializedName("photo_path")
    var photoPath: String="",
    @SerializedName("token")
    var token: String=""
):Parcelable
@Parcelize

data class Module(
    @SerializedName("can_create")
    var canCreate: Boolean=false,
    @SerializedName("can_delete")
    var canDelete: Boolean=false,
    @SerializedName("can_select")
    var canSelect: Boolean=false,
    @SerializedName("can_update")
    var canUpdate: Boolean=false,
    @SerializedName("icon_module")
    var iconModule: String="",
    @SerializedName("id_module")
    var idModule: Int=0,
    @SerializedName("name_module")
    var nameModule: String=""
):Parcelable