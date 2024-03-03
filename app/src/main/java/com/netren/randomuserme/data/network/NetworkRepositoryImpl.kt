package com.netren.randomuserme.data.network

import android.content.Context
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.netren.randomuserme.model.UserModel
import org.json.JSONObject

class NetworkRepositoryImpl(private val context: Context) : NetworkRepository {

    override fun getUsers(
        onSuccess: (List<UserModel>) -> Unit
    ) {
        val url = "https://randomuser.me/api/" +
                "?results=10"

        val queue = Volley.newRequestQueue(context)

        val stringRequest = StringRequest(
            Request.Method.GET,
            url,
            { response ->
                val tempList = getUsersFromResponse(response)

                onSuccess.invoke(tempList)
            },
            { error ->
                Toast.makeText(context, "Не удалось загрузить данные. Проверьте интернет соединение.", Toast.LENGTH_SHORT).show()
            }
        )

        queue.add(stringRequest)
    }

    private fun getUsersFromResponse(response: String): List<UserModel> {
        val list = arrayListOf<UserModel>()

        if (response.isEmpty())
            return list

        val mainObject = JSONObject(response)
        val results = mainObject.getJSONArray("results")

        for (i in 0 until results.length()) {
            val item = results[i] as JSONObject
            val userModel = getUserFromJSON(item)
            list.add(userModel)
        }
        return list
    }

    private fun getUserFromJSON(jsonObject: JSONObject): UserModel{
        val location = getLocationFromJSON(jsonObject)

        return UserModel(
            gender = jsonObject
                .getString("gender"),
            nat = jsonObject
                .getString("nat"),

            nameTittle = jsonObject
                .getJSONObject("name")
                .getString("title"),
            nameFirst = jsonObject
                .getJSONObject("name")
                .getString("first"),
            nameLast = jsonObject
                .getJSONObject("name")
                .getString("last"),

            pictureLarge = jsonObject
                .getJSONObject("picture")
                .getString("large"),
            pictureMedium = jsonObject
                .getJSONObject("picture")
                .getString("medium"),
            pictureThumbnail = jsonObject
                .getJSONObject("picture")
                .getString("thumbnail"),

            email = jsonObject
                .getString("email"),
            phone = jsonObject
                .getString("phone"),
            cell = jsonObject
                .getString("cell"),

            dob = jsonObject
                .getJSONObject("dob")
                .getString("date"),
            age = jsonObject
                .getJSONObject("dob")
                .getString("age")
                .toInt(),

            location = location,
            locationLatitude = jsonObject
                .getJSONObject("location")
                .getJSONObject("coordinates")
                .getString("latitude")
                .toDouble(),
            locationLongitude = jsonObject
                .getJSONObject("location")
                .getJSONObject("coordinates")
                .getString("longitude")
                .toDouble(),
        )
    }

    private fun getLocationFromJSON(jsonObject: JSONObject): String{
        var result = ""

        result += jsonObject
            .getJSONObject("location")
            .getJSONObject("street")
            .getString("number")

        result += " " + jsonObject
            .getJSONObject("location")
            .getJSONObject("street")
            .getString("name")

        result += ", " + jsonObject
            .getJSONObject("location")
            .getString("city")

        result += ", " + jsonObject
            .getJSONObject("location")
            .getString("state")

        result += ", " + jsonObject
            .getJSONObject("location")
            .getString("country")

        result += ", " + jsonObject
            .getJSONObject("location")
            .getString("postcode")

        return result
    }

}