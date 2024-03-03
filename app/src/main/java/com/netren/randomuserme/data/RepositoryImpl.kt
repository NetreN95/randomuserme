package com.netren.randomuserme.data

import com.netren.randomuserme.data.db.DBRepository
import com.netren.randomuserme.data.network.NetworkRepository
import com.netren.randomuserme.model.UserModel
import kotlinx.coroutines.flow.Flow

class RepositoryImpl(
    private val networkRepository: NetworkRepository,
    private val dbRepository: DBRepository
) : Repository {
    override fun getUsers(): Flow<List<UserModel>> {
        return dbRepository.getUsers()
    }

    override fun uploadUsersFromNetwork(onSuccess: (List<UserModel>) -> Unit) {
        networkRepository.getUsers() { list ->
            onSuccess.invoke(list)
        }
    }

    override suspend fun insert(userModel: UserModel) {
        dbRepository.insert(userModel)
    }

    override suspend fun insert(usersList: List<UserModel>) {
        dbRepository.insert(usersList)
    }

    override suspend fun deleteUsersFromDB() {
        dbRepository.deleteAllUsers()
    }
}