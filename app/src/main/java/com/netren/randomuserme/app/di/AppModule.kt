package com.netren.randomuserme.app.di

import com.netren.randomuserme.AppViewModel
import com.netren.randomuserme.data.Repository
import com.netren.randomuserme.data.RepositoryImpl
import com.netren.randomuserme.data.db.DBRepository
import com.netren.randomuserme.data.db.DBRepositoryImpl
import com.netren.randomuserme.data.db.room.UsersDao
import com.netren.randomuserme.data.db.room.UsersDatabase
import com.netren.randomuserme.data.network.NetworkRepository
import com.netren.randomuserme.data.network.NetworkRepositoryImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel<AppViewModel> {
        AppViewModel(repository = get())
    }

    single<Repository> {
        RepositoryImpl(
            networkRepository = get(),
            dbRepository = get()
        )
    }

    single<NetworkRepository> {
        NetworkRepositoryImpl(context = get())
    }
    single<DBRepository> {
        DBRepositoryImpl(
            usersDao = get()
        )
    }

    single<UsersDao> {
        UsersDatabase
            .getInstance(context = get())
            .getUsersDao()
    }
}