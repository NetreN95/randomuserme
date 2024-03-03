package com.netren.randomuserme.data.db.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.netren.randomuserme.model.UserModel
import kotlinx.coroutines.flow.Flow

@Dao
interface UsersDao {
    @Query("SELECT * FROM users_table")
    fun getAllUsers(): Flow<List<UserModel>>

    @Query("DELETE FROM users_table")
    suspend fun deleteAllUsers()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(userModel: UserModel)

    @Delete
    suspend fun delete(userModel: UserModel)
}