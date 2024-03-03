package com.netren.randomuserme.data.db.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.netren.randomuserme.model.UserModel

@Database(entities = [UserModel::class], version = 1)
abstract class UsersDatabase: RoomDatabase() {
    abstract fun getUsersDao(): UsersDao

    companion object {
        private var database: UsersDatabase? = null

        @Synchronized
        fun getInstance(context: Context): UsersDatabase {
            if (database == null) {
                database =
                    Room.databaseBuilder(context, UsersDatabase::class.java, "DB_UsersStorage")
                        .build()
            }
            return database as UsersDatabase
        }
    }
}