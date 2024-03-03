package com.netren.randomuserme.data.db

import com.netren.randomuserme.model.UserModel
import kotlinx.coroutines.flow.Flow

interface DBRepository {
    fun getUsers(): Flow<List<UserModel>>
    suspend fun insert(userModel: UserModel)
    suspend fun insert(usersList: List<UserModel>)
    suspend fun delete(userModel: UserModel)
    suspend fun delete(usersList: List<UserModel>)
    suspend fun deleteAllUsers()
}