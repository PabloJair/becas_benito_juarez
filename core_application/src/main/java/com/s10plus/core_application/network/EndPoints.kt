package com.s10plus.core_application.network

object EndPoints {


    const val LOGIN ="api/login"
    const val REGISTER = "api/register"


    const val GET_ALL_USER ="api/user/all"
    const val GET_MESSAGE_FOR_ID = "api/message/by/{idUser}"
    const val GET_MESSAGES_FOR_ROOM = "api/message/room/{idUser}/{idUserTo}/{date}"
    const val GET_MESSAGES_FOR_LAST_MESSAGE = "api/message/fromLastMessage/{idUser}/{idUserTo}/{date}/{idLastMessage}"
    const val SEND_MESSAGE = "api/message/send"

    const val SIGNUP = "api/auth/signup"
    const val RECOVERY_PASSWORD = "api/password/create"
    const val SEND_NEW_PASSWORD = "api/password/reset"
    const val UPDATE_PIC_PROFILE = "api/user/updatePhoto/{idUser}"
    const val UPDATE_PROFILE = "api/user/updateProfile/{idUser}"

    const val GET_ALL_ACTIVITIES ="api/assigment/for/weekend/{idUser}"

    const val SET_CHANGE_STATUS_STOP="api/assigment/stop"
    const val SET_CHANGE_STATUS_START="api/assigment/start"
    const val SET_TOKEN_FIREBASE="api/user/setfirebasetoken/{idUser}"


}