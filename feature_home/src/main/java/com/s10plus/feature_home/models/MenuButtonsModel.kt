package com.s10plus.feature_home.models

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.s10plus.core_application.base_ui.BaseFragment
import com.s10plus.feature_home.MenusCreator
import com.s10plus.feature_home.models.TypeView.*
import kotlinx.android.parcel.Parcelize

@Parcelize
class MenuButtonsModel(
    @Expose(serialize = false)
    @SerializedName("text")
    var text:String,
    @Expose(serialize = false)
    @SerializedName("idIcon")
    var idIcon:Int,
    var sendToFragment: TypeView,
    var subMenu:Array<MenuButtonsModel>?=null,
    @Expose(serialize = false)
    @SerializedName("detailsModel")
    var detailsModel: DetailsModel?=null,
    @Expose(serialize = false)
    @SerializedName("typeButton")
    var typeButton:TypeButton =TypeButton.GREEN,
    @Expose(serialize = false)
    @SerializedName("link")
    var link:String="",
    @Expose(serialize = false)
    @SerializedName("numberPhone")
    var numberPhone:String="",
    var id:Int =0,
    var otherInformation:String =when(sendToFragment){
        MENU -> "${sendToFragment.description} $text"
        DETAILS -> "${sendToFragment.description} ${text}"
        LINK -> "${sendToFragment.description} $link"
        CONTINUE_CALL -> "${sendToFragment.description} $numberPhone"
        REDES_SOCIALES->"${sendToFragment.description}: "
        WEBVIEW -> "${sendToFragment.description} $link"


    },
    var activity:String="",
    var label:String ="",
    var concept:String="",
    var parent_id:String=""
                       ):Parcelable {
}
@Parcelize
enum class TypeView(var description:String):Parcelable{
    MENU("CLICK/MENU:"),
    DETAILS("CLICK/DETALLES:"),
    LINK("CLICK/URL:"),
    CONTINUE_CALL("CLICK/CONTINUAR LLAMADA:"),
    REDES_SOCIALES("CLICK/REDES SOCIALES"),
    WEBVIEW("CLICK/URL:")


}
@Parcelize
enum class TypeButton():Parcelable{
    GREEN,
    BLACK,
    CALL,
    SN

}