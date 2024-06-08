package com.example.jass_app

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.jass_app.data.viewmodel.LoginViewModel
import com.example.jass_app.data.viewmodel.ProfileViewModel
import com.example.jass_app.data.viewmodel.RegistrationViewModel
import com.example.jass_app.di.appModule
import com.example.jass_app.ui.ComposeNav
import com.example.jass_app.ui.theme.JassappTheme
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.core.context.GlobalContext.startKoin

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val registrationViewModel = getViewModel<RegistrationViewModel>()
        val authorizationViewModel = getViewModel<LoginViewModel>()
        val profileViewModel = getViewModel<ProfileViewModel>()

        setContent {
            JassappTheme {
                // A surface container using the 'background' color from the theme
                ComposeNav(registrationViewModel, authorizationViewModel, profileViewModel)
            }
        }
    }
}

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MyApp)
            modules(appModule)
        }
    }
}
