package com.example.contactsapp.ui

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.contactsapp.R
import com.example.contactsapp.main.MainViewModel
import com.example.contactsapp.navigation.Screen
import com.example.contactsapp.utils.Constants

@Composable
fun SplashScreen(navController: NavController, mainViewModel: MainViewModel = hiltViewModel()) {

  val loading by mainViewModel.loading
  var navigationPerformed by remember { mutableStateOf(false) }

  val scale = remember {
    Animatable(1f)
  }
  LaunchedEffect(key1 = Constants.KEY_SPLASH_SCREEN) {
    scale.animateTo(
      targetValue = 0.3f,
      animationSpec = tween(
        durationMillis = 1000,
        easing = {
          OvershootInterpolator(2f).getInterpolation(it)
        }
      )
    )
  }

  if (!navigationPerformed && !loading) {
    navController.navigate(Screen.ContactsScreen.route) {
      popUpTo(Screen.SplashScreen.route) {
        inclusive = true
      }
    }

    navigationPerformed = true
  }

  Box(
    modifier = Modifier
      .fillMaxSize()
      .background(Color.Blue),
    contentAlignment = Alignment.Center
  ) {
    val textSplashScreen = stringResource(id = R.string.text_splash_screen)
    Text(text = textSplashScreen)
  }
}