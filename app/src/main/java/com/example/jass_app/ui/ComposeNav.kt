package com.example.jass_app.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jass_app.data.viewmodel.LoginViewModel
import com.example.jass_app.data.viewmodel.RegistrationViewModel
import com.example.jass_app.data.viewmodel.ProfileViewModel
import com.example.jass_app.ui.screen.Authorisation
import com.example.jass_app.ui.screen.Registration
import com.example.jass_app.ui.screen.UserProfile

@Composable
fun ComposeNav(registrationViewModel: RegistrationViewModel, authorizationViewModel: LoginViewModel, profileViewModel: ProfileViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "Authorization") {
        composable("Registration") {
            Registration(navController, registrationViewModel)
        }
        composable("Authorization") {
            Authorisation(navController, authorizationViewModel)
        }
        composable("Profile") {
            UserProfile(navController, profileViewModel)
        }
    }
}