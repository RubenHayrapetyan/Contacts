package com.example.contactsapp.ui.user

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.contactsapp.R
import com.example.contactsapp.ui.SharedViewModel
import com.example.domain.entity.local.UserEntity

@Composable
fun User(sharedViewModel: SharedViewModel) {

  Column(
    modifier = Modifier
      .fillMaxSize()
      .padding(horizontal = 16.dp, vertical = 8.dp)
  ) {
    UserContent(userEntity = sharedViewModel.user ?: return@Column)

    Spacer(modifier = Modifier.height(40.dp))

    Description(descriptionText = sharedViewModel.user?.description ?: "")
  }
}

@Composable
private fun UserContent(userEntity: UserEntity) {
  Column(
    modifier = Modifier.fillMaxWidth(),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {

    val painter = rememberAsyncImagePainter(userEntity.image)

    Image(
      modifier = Modifier
        .size(250.dp)
        .clip(CircleShape)
        .border(1.dp, MaterialTheme.colorScheme.primary, CircleShape),
      contentScale = ContentScale.FillBounds,
      painter = painter,
      contentDescription = "User"
    )

    Spacer(modifier = Modifier.height(10.dp))

    Text(text = userEntity.name)

    Spacer(modifier = Modifier.height(10.dp))

    Text(
      text = userEntity.phoneNumber,
      style = TextStyle(fontWeight = FontWeight.Bold),
    )
  }
}

@Composable
private fun Description(descriptionText: String) {
  Text(
    text = stringResource(id = R.string.description),
    style = TextStyle(
      fontWeight = FontWeight.Bold,
      fontSize = 20.sp
    ),
  )

  Spacer(modifier = Modifier.height(6.dp))

  Text(text = descriptionText)
}