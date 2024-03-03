package com.netren.randomuserme.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.netren.randomuserme.R
import com.netren.randomuserme.model.UserModel

@Composable
fun UserListScreen(
    userList: State<List<UserModel>>,
    currentUser: MutableState<UserModel>,
    onClickUpload: () -> Unit,
    onClickDelete: () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            itemsIndexed(
                userList.value
            ) { _, item ->
                UserItemInList(item = item, currentUser = currentUser)
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomEnd) {
        Column {
            IconButton(
                onClick = { onClickUpload.invoke() },
                modifier = Modifier
                    .size(75.dp)
                    .padding(bottom = 10.dp, end = 10.dp),
                colors = IconButtonDefaults.iconButtonColors(containerColor = Color.White)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_download),
                    contentDescription = "Upload",
                    tint = Color.Green,
                    modifier = Modifier.fillMaxSize()
                )
            }

            if (userList.value.isNotEmpty()) {
                IconButton(
                    onClick = {
                        onClickDelete.invoke()
                    },
                    modifier = Modifier
                        .size(75.dp)
                        .padding(bottom = 10.dp, end = 10.dp),
                    colors = IconButtonDefaults.iconButtonColors(containerColor = Color.Green)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_delete),
                        contentDescription = "Delete",
                        tint = Color.White,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    }
}

@Composable
fun UserItemInList(item: UserModel, currentUser: MutableState<UserModel>) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 3.dp)
            .clickable {
                currentUser.value = item
            },
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        shape = RoundedCornerShape(5.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            AsyncImage(
                model = item.pictureMedium,
                contentDescription = "image1",
                modifier = Modifier
                    .size(35.dp)
                    .padding(end = 8.dp)
            )
            Text(
                text =
                "${item.nameFirst} ${item.nameLast}",
                style = TextStyle(fontSize = 10.sp)
            )
        }
    }
}