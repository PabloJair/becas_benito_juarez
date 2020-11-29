package com.s10plus.feature_home.models

import android.os.Parcelable
import com.s10plus.core_application.base_ui.BaseFragment
import kotlinx.android.parcel.Parcelize

@Parcelize
class MenuButtonsModel(
                       var text:String,
                       var idIcon:Int,
                       var sendToFragment: TypeView,
                       var subMenu:Array<MenuButtonsModel>?=null,
                       var detailsModel: DetailsModel?=null,
                       var typeButton:TypeButton =TypeButton.GREEN,
                       var link:String=""
                       ):Parcelable {
}
@Parcelize
enum class TypeView():Parcelable{
    MENU,
    DETAILS,
    MENU_BLACK,
    LINK
}
@Parcelize
enum class TypeButton():Parcelable{
    GREEN,
    BLACK
}