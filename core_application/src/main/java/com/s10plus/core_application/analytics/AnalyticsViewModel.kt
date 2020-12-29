package com.s10plus.core_application.analytics

import com.s10plus.core_application.base_ui.BaseViewModel
import io.reactivex.functions.Consumer

class AnalyticsViewModel:BaseViewModel() {
    private var service = serverRetrofit.getService(AnalyticsService::class.java)

    fun sendClicks(id:Int,other_information:String ){



        setupSubscribe(service.sendClick(RQ = RequestAnalytics(id.toString(),other_information)),
            {
                loader.value = false

                if (it.status == 200)
                    success.value = it.data
                else
                    error.value = it.message

            },
            {
                loader.value = false
                error.value="Las credenciales son invalidas"

            })

    }

}