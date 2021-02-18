package com.s10plus.becas.feature_main.view_model

import com.s10plus.core_application.GlobalSettings
import com.s10plus.core_application.base_ui.BaseViewModel
import com.s10plus.core_application.mocks.Mocks
import com.s10plus.core_application.models.ItemMenu
import com.s10plus.core_application.models.UserInformation

class MainViewModel:BaseViewModel(){


    private var service = serverRetrofit.getService(MainService::class.java)



    fun load(){


        loader.value = true
        setupSubscribe(service.load(),
            {
                loader.value = false
                //GlobalSettings.setToken("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2MDQ4NjM5NzgsImV4cCI6MTYwNDg2NzU3OCwiYXVkIjoiNzU1YjU3MDhkN2IxMDhkODE2YzViOTZlOGJkMDExMGU1MzAxMWRhOSIsImRhdGEiOnsicm9sIjoiMSIsImlkX3VzdWFyaW8iOiJ1c2VyMSIsInVzZXJOYW1lIjoiYWxmb25zb2xvcGV6IiwiZnVsbFVzZXJOYW1lIjoiQWxmb25zbyBMXHUwMGYzcGV6IiwiaWRJbnN0aXR1Y2lvbiI6bnVsbH19.ioU-ZSzdaVDX8FcQXLNuKmFesFnmDsxQDS0iSecD6_4")
                if(it.status ==200) {
                    success.value = it.token
                    GlobalSettings.setToken(it.token!!)
                }

                else
                    error.value=it.message

            },
            {
                loader.value = false
                error.value=it.message
               // GlobalSettings.setToken("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2MDQ4NjM5NzgsImV4cCI6MTYwNDg2NzU3OCwiYXVkIjoiNzU1YjU3MDhkN2IxMDhkODE2YzViOTZlOGJkMDExMGU1MzAxMWRhOSIsImRhdGEiOnsicm9sIjoiMSIsImlkX3VzdWFyaW8iOiJ1c2VyMSIsInVzZXJOYW1lIjoiYWxmb25zb2xvcGV6IiwiZnVsbFVzZXJOYW1lIjoiQWxmb25zbyBMXHUwMGYzcGV6IiwiaWRJbnN0aXR1Y2lvbiI6bnVsbH19.ioU-ZSzdaVDX8FcQXLNuKmFesFnmDsxQDS0iSecD6_4")


            })

    }

}