package com.cherry.composenotes.presentation.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.List
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.cherry.composenotes.presentation.NoteViewModel
import com.cherry.composenotes.presentation.components.CustomDialog
import com.cherry.composenotes.presentation.components.NoteItem
import com.cherry.composenotes.presentation.screens.navigation.Screen


@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun NoteListScreen(navController: NavHostController, viewModel: NoteViewModel) {
    val state = viewModel.state.value

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier.scale(0.8f),
                shape = CircleShape,
                onClick = {
                    navController.navigate(Screen.AddEditNoteScreen.route)
                }
            ) {
                Icon(imageVector = Icons.Rounded.Add, contentDescription = "")
            }
        }
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(8.dp)
        ) {
            items(state.notes) { note ->
                NoteItem(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
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
                )
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