package com.s10plus.core_application.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.s10plus.core_application.database.enties.User

@Database(entities =
[
    User::class
],version = 1)

@TypeConverters()
abstract  class S10PlusDatabase:RoomDatabase(){




}