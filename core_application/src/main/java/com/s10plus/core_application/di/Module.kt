package com.s10plus.core_application.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import androidx.room.Room.databaseBuilder
import com.google.gson.Gson
import com.s10plus.core_application.database.S10PlusDatabase
import com.s10plus.core_application.database.enties.User
import com.s10plus.core_application.models.UserInformation
import com.s10plus.core_application.network.ClientRetrofit
import com.s10plus.core_application.utils.Constans
import org.koin.android.ext.koin.androidApplication
import org.koin.core.qualifier.named
import org.koin.dsl.koinApplication
import org.koin.dsl.module

val databaseModule = module {
    single{ databaseBuilder(get(),S10PlusDatabase::class.java,"S10Plus.db").fallbackToDestructiveMigration().build() }
    single{ ClientRetrofit}


}

const val TOKEN_USER = "TOKEN_USER"
const val LOGIN_USER_FOR_CACHE = "LOGIN_USER_FOR_CACHE"
val applicationModule= module {

    single { getProperty(Constans.USER_DATA) as UserInformation }



}
