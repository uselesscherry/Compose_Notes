@file:OptIn(ExperimentalMaterial3Api::class)

package com.cherry.composenotes.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog


@Composable
fun CustomDialog(onDeleteConfirm: () -> Unit, onDeleteDismiss: () -> Unit) {
    Dialog(onDismissRequest = onDeleteDismiss) {
        CustomDialogUI(onDeleteConfirm = onDeleteConfirm, onDeleteDismiss = onDeleteDismiss)
    }
}

//Layout
@Composable
fun CustomDialogUI(
    titleText: String = "Delete note?",
    messageText: String = "Are you sure you want to delete note? There will be no way to restore it.",
    onDeleteConfirm: () -> Unit,
    onDeleteDismiss: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(
            topStartPercent = 12,
            topEndPercent = 4,
            bottomStartPercent = 4,
            bottomEndPercent = 12
        )
    ) {

        Column {
            Icon(
                imageVector = Icons.Rounded.Delete,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .padding(top = 35.dp)
                    .height(70.dp)
                    .fillMaxWidth(),

                )

            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = titleText,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(top = 5.dp)
                        .fillMaxWidth(),
                    style = MaterialTheme.typography.headlineSmall,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = messageText,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(top = 10.dp, start = 25.dp, end = 25.dp)
                        .fillMaxWidth(),
                    style = MaterialTheme.typography.bodyLarge
                )
            }

            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
                    .background(MaterialTheme.colorScheme.tertiaryContainer),
                horizontalArrangement = Arrangement.SpaceAround
            ) {

                TextButton(onClick = onDeleteDismiss) {

                    Text(
                        "Cancel",
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.secondary,
                        modifier = Modifier.padding(top = 5.dp, bottom = 5.dp)
                    )
                }
                TextButton(onClick = onDeleteConfirm) {
                    Text(
                        "Delete",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.W900,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(top = 5.dp, bottom = 5.dp)
                    )
                }
            }
        }
    }
}

