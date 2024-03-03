package com.netren.randomuserme

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.netren.randomuserme.data.Repository
import com.netren.randomuserme.model.UserModel
import kotlinx.coroutines.launch

class AppViewModel(private val repository: Repository) : ViewModel() {
    var userList = repository.getUsers()

    fun uploadUsersFromNetwork() = viewModelScope.launch {
        repository.uploadUsersFromNetwork { list ->
            insert(list)
        }
    }

    private fun insert(userModel: UserModel) = viewModelScope.launch {
        repository.insert(userModel)
    }

    private fun insert(usersList: List<UserModel>) = viewModelScope.launch {
        repository.insert(usersList)
    }

    fun deleteUsersFromDB() = viewModelScope.launch {
        repository.deleteUsersFromDB()
    }
}