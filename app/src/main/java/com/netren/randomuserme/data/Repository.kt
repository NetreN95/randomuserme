package com.netren.randomuserme.data

import com.netren.randomuserme.model.UserModel
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun getUsers(): Flow<List<UserModel>>

    fun uploadUsersFromNetwork(onSuccess: (List<UserModel>) -> Unit)

    suspend fun insert(userModel: UserModel)
    suspend fun insert(usersList: List<UserModel>)
    suspend fun deleteUsersFromDB()
}