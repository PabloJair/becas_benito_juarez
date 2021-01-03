package com.s10plus.core_application.models
import android.os.Parcelable
import android.view.View
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
        @SerializedName("menu")
    var modules: List<ItemMenu> = arrayListOf(),
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

data class ItemMenu(
        @SerializedName("icon")
    var icon: String="",
        @SerializedName("id_item")
    var id_item: Int=0,
        @SerializedName("text_item")
    var text_item: String="",
):Parcelable,AbstractComponentModel(){
    override fun onConfigView(view: View) {
    }


}