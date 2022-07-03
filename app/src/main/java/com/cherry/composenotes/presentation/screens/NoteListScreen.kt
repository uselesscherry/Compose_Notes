package com.cherry.composenotes.presentation.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.List
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.cherry.composenotes.presentation.NoteViewModel
import com.cherry.composenotes.presentation.components.CustomDialog
import com.cherry.composenotes.presentation.components.NoteItem
import com.cherry.composenotes.presentation.screens.navigation.Screen


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NoteListScreen(navController: NavHostController, viewModel: NoteViewModel) {
    val state = viewModel.state.value
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .weight(9f)
                .fillMaxWidth()
                .fillMaxHeight(),
            contentPadding = PaddingValues(8.dp)
        ) {
            items(state.notes) { note ->
                NoteItem(
                    modifier = Modifier
                        .padding(8.dp)
                        .combinedClickable(
                            onClick = {
                                note.id?.let {
                                    navController.navigate(Screen.AddEditNoteScreen.withArgument(it))
                                }
                            },

                            onLongClick = {
                                note.id?.let { deleteNoteId ->
                                    viewModel.showDeleteAlert(deleteNoteId)
                                }
                            }
                        ),
                    note = note,

                    /* onClick = {
                         note.id?.let {
                             navController.navigate(Screen.AddEditNoteScreen.withArgument(it))
                         }
                     }*/
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.75f)
                .fillMaxHeight()
                .background(MaterialTheme.colorScheme.tertiaryContainer),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Icon(imageVector = Icons.Rounded.List, contentDescription = "")
            IconButton(onClick = {
                navController.navigate(Screen.AddEditNoteScreen.route)
            }) {
                Icon(imageVector = Icons.Rounded.Add, contentDescription = "")
            }
        }
    }
    if (state.isAlertDialogShown) {
        CustomDialog(onDeleteConfirm = {
            state.deleteNoteId?.let { deleteNoteId ->
                viewModel.deleteNote(deleteNoteId)
            }
        }, onDeleteDismiss = {
            viewModel.dismissDeleting()
        })
    }
}