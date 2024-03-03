package com.netren.randomuserme.screens

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.netren.randomuserme.AppViewModel
import com.netren.randomuserme.MainActivity
import com.netren.randomuserme.model.UserModel
import com.netren.randomuserme.ui.theme.RandomUserMeTheme

@Composable
fun AppScreen(
    context: Context,
    viewModel: AppViewModel
) {
    val userList = viewModel.userList.collectAsState(initial = emptyList())

    val currentUser: MutableState<UserModel> = remember {
        mutableStateOf(UserModel())
    }

    if ((currentUser.value.nameFirst + currentUser.value.nameLast).isEmpty()) {
        (context as MainActivity).onBackPressed = null
    } else
        (context as MainActivity).onBackPressed = {
            currentUser.value = UserModel()
        }

    val startActivity = { uriString: String ->
        val intent = Intent(Intent.ACTION_VIEW)
        intent.setData(
            Uri.parse(uriString)
        )
        context.startActivity(intent)
    }


    RandomUserMeTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            if ((currentUser.value.nameFirst + currentUser.value.nameLast).isEmpty()) {
                UserListScreen(
                    userList = userList,
                    currentUser = currentUser,
                    onClickUpload = { viewModel.uploadUsersFromNetwork() },
                    onClickDelete = { viewModel.deleteUsersFromDB() }
                )
            } else {
                UserScreen(
                    currentUser = currentUser.value,
                    startActivity = startActivity
                )
            }
        }
    }
}