package com.s10plus.feature_home.models

object AnalitycsUtils {


    fun menuButtonsModelToLog(array: Array<MenuButtonsModel>, level:Int =0):String{
        val sb = StringBuffer()

        array.forEach {

            sb.append("${it.id}|1|opened|${it.activity}|${it.label}|${it.concept}|${(level)}|${it.parent_id}")
            sb.append(System.lineSeparator());

            if(it.sendToFragment ==TypeView.MENU) {
                if(!it.subMenu.isNullOrEmpty())
                    sb.append(menuButtonsModelToLog(it.subMenu!!,level + 1))


            }else if (it.sendToFragment == TypeView.DETAILS){
                if(it.detailsModel!=null) {

                    sb.append(detailsModelToLog(it.detailsModel!!, level + 1))
                }

            }
        }

        return sb.toString()

    }

    fun detailsModelToLog(
        detailsModel: DetailsModel,
        level:Int =0):String{
        val sb = StringBuffer()
        detailsModel.texts.forEach {

            if(it.sender) {
                sb.append("${it.id}|1|nav|${it.activity}|${it.label}|${it.concept}|${level}|${it.parent_id}")
                sb.append(System.lineSeparator());
            }


        }

        return  sb.toString();
    }
}