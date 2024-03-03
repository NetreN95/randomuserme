package com.netren.randomuserme.data.db

import com.netren.randomuserme.data.db.room.UsersDao
import com.netren.randomuserme.model.UserModel
import kotlinx.coroutines.flow.Flow

class DBRepositoryImpl(private val usersDao: UsersDao): DBRepository {
    override fun getUsers(): Flow<List<UserModel>> {
        return usersDao.getAllUsers()
    }

    override suspend fun insert(userModel: UserModel) {
        usersDao.insert(userModel)
    }

    override suspend fun insert(usersList: List<UserModel>) {
        usersList.forEach(){
            insert(it)
        }
    }

    override suspend fun delete(userModel: UserModel) {
        usersDao.delete(userModel)
    }

    override suspend fun delete(usersList: List<UserModel>) {
        usersList.forEach(){
            delete(it)
        }
    }

    override suspend fun deleteAllUsers() {
        usersDao.deleteAllUsers()
    }
}