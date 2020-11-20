package com.s10plus.core_application.models

data class PreConfigurationModel(
        var menuModel: MenuModel= MenuModel(),
        var views:ArrayList<ViewModelS10Plus> = arrayListOf(),
        var companyModel: CompanyModel =CompanyModel(),
        var version:String="V1.1"
)