package com.netren.randomuserme.data.network

import com.netren.randomuserme.model.UserModel

interface NetworkRepository {
    fun getUsers(
        onSuccess: (list: List<UserModel> ) -> Unit
    )
}