package com.netren.randomuserme.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users_table")
data class UserModel(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    @ColumnInfo
    var gender: String = "",
    @ColumnInfo
    var nat: String = "",

    @ColumnInfo
    var nameTittle: String = "",
    @ColumnInfo
    var nameFirst: String = "",
    @ColumnInfo
    var nameLast: String = "",

    @ColumnInfo
    var pictureLarge: String = "",
    @ColumnInfo
    var pictureMedium: String = "",
    @ColumnInfo
    var pictureThumbnail: String = "",

    @ColumnInfo
    var email: String = "",
    @ColumnInfo
    var phone: String = "",
    @ColumnInfo
    var cell: String = "",

    @ColumnInfo
    var dob: String = "",
    @ColumnInfo
    var age: Int = 0,

    @ColumnInfo
    var location: String = "",
    @ColumnInfo
    var locationLatitude: Double = 0.0,
    @ColumnInfo
    var locationLongitude: Double = 0.0
)
