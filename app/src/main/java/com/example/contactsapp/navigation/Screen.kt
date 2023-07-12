package com.example.contactsapp.navigation

sealed class Screen(val route: String) {
  object SplashScreen : Screen("splash_screen")
  object ContactsScreen : Screen("contacts_screen")
  object UserScreen : Screen("user_screen")
}