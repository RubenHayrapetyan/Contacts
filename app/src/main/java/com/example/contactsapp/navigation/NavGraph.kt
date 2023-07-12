package com.example.contactsapp.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.contactsapp.ui.SharedViewModel
import com.example.contactsapp.ui.SplashScreen
import com.example.contactsapp.ui.contacts.ContactsScreen
import com.example.contactsapp.ui.user.User

@Composable
fun NavGraph(navController: NavHostController) {

  val sharedViewModel: SharedViewModel = viewModel()

  NavHost(
    navController = navController,
    startDestination = Screen.SplashScreen.route
  ) {
    composable(route = Screen.SplashScreen.route) {
      SplashScreen(navController = navController)
    }
    composable(route = Screen.ContactsScreen.route) {
      ContactsScreen(navController = navController, sharedViewModel = sharedViewModel)
    }
    composable(Screen.UserScreen.route) {
      User(sharedViewModel = sharedViewModel)
    }
  }
}