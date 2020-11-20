package com.s10plus.becas.feature_main.view_model

import com.s10plus.core_application.base_ui.BaseViewModel
import com.s10plus.core_application.mocks.Mocks
import com.s10plus.core_application.models.ItemMenu
import com.s10plus.core_application.models.UserInformation

class MainViewModel:BaseViewModel(){


    private var service = serverRetrofit.getService(MainService::class.java)

    fun getModules(idUser:Int,idCompany:Int){





        success.value = UserInformation(email ="pablo.angeles@s10plus.com", modules = Mocks.menuItemMock())


    }


}