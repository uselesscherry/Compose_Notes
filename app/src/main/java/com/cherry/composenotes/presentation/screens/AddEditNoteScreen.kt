package com.cherry.composenotes.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AddCircle
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.cherry.composenotes.presentation.NoteViewModel
import com.cherry.composenotes.presentation.screens.navigation.Screen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditNoteScreen(id: Int = -1, navController: NavHostController, viewModel: NoteViewModel) {
    val context = LocalContext.current

    var title by remember {
        mutableStateOf("")
    }
    var content by remember {
        mutableStateOf("")
    }

    if (id != -1) {
        LaunchedEffect(Unit) {
            val note = viewModel.getNoteById(id)
            title = note.title
            content = note.content
        }
    }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            FilledTonalButton(onClick = {
                viewModel.insertNote(id, title, content, context) {
                    navController.navigate(Screen.NoteList.route) {
                        popUpTo(Screen.NoteList.route) {
                            inclusive = true
                        }
                    }
                }
            }) {
                Icon(imageVector = Icons.Rounded.AddCircle, contentDescription = "")
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "add note")
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 12.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(horizontal = 16.dp),
                value = title,
                onValueChange = {
                    title = it
                },
                maxLines = 1,
                singleLine = true,
                label = {
                    Text(text = "Title")
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(10f)
                    .padding(horizontal = 16.dp),
                value = content,
                onValueChange = {
                    content = it
                }, label = {
                    Text(text = "Your notes")
                }
            )
        }
    }
}