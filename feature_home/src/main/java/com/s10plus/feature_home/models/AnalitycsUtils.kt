package com.s10plus.feature_home.models

object AnalitycsUtils {


    fun menuButtonsModelToLog(array: Array<MenuButtonsModel>,  parentMenuButton: MenuButtonsModel? =null):String{
        val sb = StringBuffer()

        array.toList().sortedBy { it.id }
        .forEach {
            if(it.detailsModel != null) {
                sb.append(detailsModelToLog(it.detailsModel!!, parentMenuButton?:it))
                sb.append("${it.id}|1|opened|${it.activity}|${it.label}|${it.concept}|${(parentMenuButton?.id?:0)}|${it.parent_id}")
                sb.append(System.lineSeparator());
            }else{
                sb.append("${it.id}|1|opened|${it.activity}|${it.label}|${it.concept}|${(parentMenuButton?.id?:0)}|${it.parent_id}")
                sb.append(System.lineSeparator());
                if(!it.subMenu.isNullOrEmpty())
                    it.subMenu!!.forEach { sb.append(menuButtonsModelToLog(arrayOf(it),it)) }
            }
        }

        return sb.toString()

    }

    fun detailsModelToLog(
        detailsModel: DetailsModel,
        parentMenuButton: MenuButtonsModel,
    ):String{
        val sb = StringBuffer()
        detailsModel.texts.forEach {

            sb.append("${it.id}|1|nav|${it.activity}|${it.label}|${it.concept}|${parentMenuButton.id}|${it.parent_id}")
            sb.append(System.lineSeparator());


        }

        return  sb.toString();
    }
}