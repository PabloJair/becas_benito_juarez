package com.s10plus.core_application.database.enties

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.s10plus.core_application.database.ConstantsDb

@Entity(tableName = ConstantsDb.USUER_NAME_TABLE)
data class User (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "user_id")
    val userId: Int?=null,
    @ColumnInfo(name = "username")
    val userName: String,
    @ColumnInfo(name = "password")
    val password:String,
    @ColumnInfo(name = "name")
    val name:String

){






}