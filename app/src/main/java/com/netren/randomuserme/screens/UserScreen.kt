package com.netren.randomuserme.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.netren.randomuserme.model.UserModel

@Composable
fun UserScreen(
    currentUser: UserModel,
//    startActivity: (Intent) -> Unit
    startActivity: (String) -> Unit
) {
    Column {
        Row(modifier = Modifier.fillMaxHeight(0.33f)) {
            Column(modifier = Modifier.fillMaxWidth()) {
                AsyncImage(
                    modifier = Modifier.fillMaxSize(),
                    model = currentUser.pictureLarge,
                    contentDescription = "UserPhoto",
                    alignment = Alignment.TopCenter
                )
            }
        }
        RowInfo(
            nameParam = "Name",
            valueParam = "${currentUser.nameTittle} ${currentUser.nameFirst} ${currentUser.nameLast}"
        )
        RowInfo(nameParam = "Gender", valueParam = currentUser.gender)
        RowInfo(nameParam = "Nat", valueParam = currentUser.nat)
        RowInfo(nameParam = "Birthday", valueParam = currentUser.dob)
        if (currentUser.age > 0) {
            RowInfo(nameParam = "Age", valueParam = currentUser.age.toString())
        }
        RowInfo(nameParam = "Location", valueParam = currentUser.location, onClick = {
            startActivity.invoke("geo:${currentUser.locationLatitude}, ${currentUser.locationLongitude}")
        })
        RowInfo(nameParam = "Phone", valueParam = currentUser.phone, onClick = {
            startActivity.invoke("tel:${currentUser.phone}")
        })
        RowInfo(nameParam = "Cell", valueParam = currentUser.cell, onClick = {
            startActivity.invoke("tel:${currentUser.cell}")
        })
        RowInfo(nameParam = "Email", valueParam = currentUser.email, onClick = {
            startActivity.invoke("mailto:${currentUser.email}")
        })
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RowInfo(nameParam: String, valueParam: String, onClick: (() -> Unit)? = null) {
    if (valueParam.isEmpty()) {
        return
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(0.25f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = nameParam)
        }

        Card(modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            onClick = { onClick?.invoke() }) {

            var colorOfText = Color.Black
            if (onClick != null) {
                colorOfText = Color.Blue
            }

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = valueParam, color = colorOfText
                )
            }
        }
    }


}

@Preview(showBackground = true)
@Composable
fun Test() {
    val startActivity = { intent: Intent ->
//        context.startActivity(intent)
    }

    val currentUser = UserModel(
        gender = "male",
        nameTittle = "Mr",
        nameFirst = "Max",
        nameLast = "Tere",
        nat = "RU",
        pictureLarge = "https://randomuser.me/api/portraits/men/75.jpg",
        email = "jennie.nichols@example.com",
        phone = "(272) 790-0888",
        cell = "(489) 330-2385",
        age = 28,
        dob = "1995-07-12T06:17:16.688Z",
        location = "8929 Valwood Pkwy, Billings, Michigan, United States, 63104",
        locationLongitude = 134.8719,
        locationLatitude = -69.8246
    )

    Column {
        Row(modifier = Modifier.fillMaxHeight(0.33f)) {
            Column(modifier = Modifier.fillMaxWidth()) {
                AsyncImage(
                    modifier = Modifier.fillMaxSize(),
                    model = currentUser.pictureLarge,
                    contentDescription = "UserPhoto",
                    alignment = Alignment.TopCenter
                )
            }
        }
        RowInfo(
            nameParam = "Name",
            valueParam = "${currentUser.nameTittle} ${currentUser.nameFirst} ${currentUser.nameLast}"
        )
        RowInfo(nameParam = "Gender", valueParam = currentUser.gender)
        RowInfo(nameParam = "Nat", valueParam = currentUser.nat)
        RowInfo(nameParam = "Birthday", valueParam = currentUser.dob)
        if (currentUser.age > 0) {
            RowInfo(nameParam = "Age", valueParam = currentUser.age.toString())
        }
        RowInfo(nameParam = "Location", valueParam = currentUser.location, onClick = {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.setData(
                Uri
                    .parse("geo:${currentUser.locationLatitude}, ${currentUser.locationLongitude}")
            )
            startActivity.invoke(intent)
        })
        RowInfo(nameParam = "Phone", valueParam = currentUser.phone, onClick = {


        })
        RowInfo(nameParam = "Cell", valueParam = currentUser.cell, onClick = {

        })
        RowInfo(nameParam = "Email", valueParam = currentUser.email, onClick = {

        })
    }
}
