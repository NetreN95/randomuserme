package com.netren.randomuserme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.netren.randomuserme.screens.AppScreen
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    var onBackPressed: (() -> Unit)? = null
    private val viewModel by viewModel<AppViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppScreen(
                context = this,
                viewModel = viewModel
            )
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        if (onBackPressed == null)
            super.onBackPressed()
        else
            onBackPressed!!.invoke()
    }
}