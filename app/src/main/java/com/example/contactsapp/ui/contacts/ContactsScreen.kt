package com.example.contactsapp.ui.contacts

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.contactsapp.R
import com.example.contactsapp.navigation.Screen
import com.example.contactsapp.ui.SharedViewModel
import com.example.contactsapp.utils.Constants
import com.example.domain.entity.local.SearchedHistoryEntity
import com.example.domain.entity.local.UserEntity

@Composable
fun ContactsScreen(
  sharedViewModel: SharedViewModel,
  navController: NavController,
  viewModel: ContactsViewModel = hiltViewModel(),
) {

  Scaffold(
    topBar = {
      Search(viewModel = viewModel)
    },
  ) { innerPadding ->
    Box(modifier = Modifier.padding(innerPadding)) {

      if (viewModel.searchedUserNotFound.value) {
        UserNotFound()
      } else {
        Users(
          sharedViewModel = sharedViewModel,
          usersLocal = viewModel.users.value,
          navController = navController
        )
      }
    }
  }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Search(viewModel: ContactsViewModel) {
  val searchedHistory by viewModel.searchedHistory
  val searchedQuery by viewModel.searchedQuery
  val textSearch = stringResource(id = R.string.text_search)
  val isBackEnabled by lazy { mutableStateOf(true) }

  LaunchedEffect(key1 = Constants.KEY_GET_ALL_SEARCHED_HISTORY) {
    viewModel.getAllSearchedHistory()
  }

  SearchBar(
    modifier = Modifier.fillMaxWidth(),
    query = searchedQuery,
    onQueryChange = {
      viewModel.searchedQuery.value = it
      viewModel.isCloseIcon.value = searchedQuery.isNotEmpty()
    },
    onSearch = {
      viewModel.getSearchedUsersFromDb(searchedQuery = it)
      viewModel.insertQueryIntoDbAndGetAllSearchedHistory(query = it)
      viewModel.isActive.value = false
    },
    active = viewModel.isActive.value,
    placeholder = {
      Text(text = textSearch)
    },
    onActiveChange = {
      viewModel.isActive.value = it
    },
    leadingIcon = {
      Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
    },
    trailingIcon = {
      if (viewModel.isCloseIcon.value) {
        Icon(
          modifier = Modifier.clickable {
            if (searchedQuery.isNotEmpty()) {
              viewModel.getUsersFromDb()
              viewModel.searchedQuery.value = ""
              viewModel.isCloseIcon.value = viewModel.isActive.value
            } else {
              viewModel.isActive.value = false
              viewModel.isCloseIcon.value = false
            }
          },
          imageVector = Icons.Default.Close, contentDescription = "Close"
        )
      }
    }
  ) {

    BackHandler(
      enabled = isBackEnabled.value
    ) {
      isBackEnabled.value = false
      viewModel.isActive.value = false
      viewModel.isCloseIcon.value = viewModel.searchedQuery.value.isNotEmpty()
      viewModel.getSearchedUsersFromDb(searchedQuery = viewModel.searchedQuery.value)
      viewModel.insertQueryIntoDbAndGetAllSearchedHistory(query = viewModel.searchedQuery.value)
    }

    TextButton(
      modifier = Modifier.align(Alignment.End),
      onClick = {
        viewModel.deleteAllSearchedHistory()
      }) {
      Text(text = stringResource(id = R.string.clear_history))
    }

    LazyColumn {
      items(
        count = searchedHistory.size,
        key = {
          searchedHistory[it].id
        },
        itemContent = { index ->
          SearchedHistoryItem(
            viewModel = viewModel,
            searchedHistory = searchedHistory[index],
          )
        }
      )
    }
  }
}

@Composable
private fun SearchedHistoryItem(
  viewModel: ContactsViewModel,
  searchedHistory: SearchedHistoryEntity
) {

  Row(
    modifier = Modifier
      .fillMaxWidth()
      .padding(horizontal = 16.dp, vertical = 8.dp)
      .clickable {
        viewModel.searchedQuery.value = searchedHistory.query
        viewModel.getSearchedUsersFromDb(searchedQuery = searchedHistory.query)
        viewModel.isActive.value = false
        viewModel.isCloseIcon.value = searchedHistory.query.isNotEmpty()
      }
  ) {

    Icon(imageVector = Icons.Default.History, contentDescription = "Search")

    Text(modifier = Modifier.padding(start = 8.dp), text = searchedHistory.query)

    Spacer(modifier = Modifier.weight(1f))

    Icon(
      modifier = Modifier.clickable {
        viewModel.deleteSearchedHistory(searchedHistoryId = searchedHistory.id)
      },
      imageVector = Icons.Default.Delete,
      contentDescription = "Delete"
    )
  }
}

@Composable
private fun Users(
  sharedViewModel: SharedViewModel,
  usersLocal: List<UserEntity>,
  navController: NavController,
) {
  LazyColumn {
    items(
      count = usersLocal.size,
      key = {
        usersLocal[it].id
      },
      itemContent = { index ->
        val user = usersLocal[index]
        UserContent(
          sharedViewModel = sharedViewModel,
          userEntity = user,
          navController = navController
        )
      }
    )
  }
}

@Composable
private fun UserContent(
  sharedViewModel: SharedViewModel,
  userEntity: UserEntity,
  navController: NavController
) {
  val painter = rememberAsyncImagePainter(userEntity.image)

  Row(
    modifier = Modifier
      .fillMaxWidth()
      .padding(all = 8.dp)
      .clickable {
        sharedViewModel.addUser(userEntity = userEntity)
        navController.navigate(Screen.UserScreen.route)
      }
  ) {
    Image(
      painter = painter,
      contentDescription = "Users icon",
      modifier = Modifier
        .size(80.dp)
        .clip(CircleShape)
        .border(1.dp, MaterialTheme.colorScheme.primary, CircleShape),
      contentScale = ContentScale.FillBounds
    )

    Spacer(modifier = Modifier.width(8.dp))

    Column(Modifier.align(Alignment.CenterVertically)) {
      Text(
        text = userEntity.name,
        color = MaterialTheme.colorScheme.secondary,
        fontSize = 18.sp
      )

      Spacer(modifier = Modifier.height(12.dp))

      Text(
        text = userEntity.phoneNumber,
        style = MaterialTheme.typography.bodyMedium,
        fontSize = 20.sp
      )
    }
  }
}

@Composable
private fun UserNotFound() {
  val textUserIsNotFound = stringResource(id = R.string.user_is_not_found)

  Box(
    modifier = Modifier
      .fillMaxWidth()
      .padding(top = 20.dp),
    contentAlignment = Alignment.Center
  ) {
    Text(text = textUserIsNotFound)
  }
}