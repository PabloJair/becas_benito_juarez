package com.s10plus.core_application.models

import com.google.gson.annotations.SerializedName

enum class TypeComponent {
    @SerializedName("button")
    BUTTON,
    @SerializedName("textview")
    TEXTVIEW,
    @SerializedName("label")
    LABEL,
    @SerializedName("carrusel")
    CARRUSEL,
    @SerializedName("image")
    IMAGE,
    @SerializedName("menu")
    MENU,
    @SerializedName("menuitem")
    MENU_ITEM,
    @SerializedName("")
    NONE
}